package images.client.plugin

import grails.converters.JSON
import grails.converters.XML
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartRequest

class ImageClientController {

    def imagesWebService

    def uploadFromCSVFragment() {
    }

    def uploadImagesFromCSVFile() {
        // it should contain a file parameter
        MultipartRequest req = request as MultipartRequest
        if (req) {
            MultipartFile file = req.getFile('csvfile')
            if (!file || file.size == 0) {
                renderResults([success: false, message: 'csvfile parameter not found, or empty. Please supply an csv file.'])
                return
            }

            // need to convert the csv file into a list of maps...
            int lineCount = 0
            def headers = []
            def batch = []
            def results = [:]

            def sendBatch = { List imageMaps ->
                println imageMaps
                def batchResults = imagesWebService.uploadImages(imageMaps)
                if (batchResults?.success) {
                    batchResults?.keySet()?.each { key ->
                        results[key] = batchResults[key]
                    }
                } else {
                    println batchResults
                }
            }

            file.inputStream.eachCsvLine { tokens ->
                if (lineCount == 0) {
                    headers = tokens
                } else {
                    def m = [:]
                    for (int i = 0; i < headers.size(); ++i) {
                        m[headers[i]] = tokens[i]
                    }
                    batch << m
                }

                if (lineCount++ % 10 == 0) {
                    sendBatch(batch)
                    batch = []
                }
            }

            if (batch) {
                sendBatch(batch)
            }

            renderResults([success: true, message: "", results: results])

        } else {
            renderResults([success: false, message: "Expected multipart request containing 'csvfile' file parameter"])
        }

    }

    private renderResults(Object results, int responseCode = 200) {

        withFormat {
            json {
                def jsonStr = results as JSON
                if (params.callback) {
                    render("${params.callback}(${jsonStr})")
                } else {
                    render(jsonStr)
                }
            }
            xml {
                render(results as XML)
            }
        }
        response.addHeader("Access-Control-Allow-Origin", "")
        response.status = responseCode
    }

}
