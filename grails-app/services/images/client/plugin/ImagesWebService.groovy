package images.client.plugin

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.Method
import groovyx.net.http.RESTClient
import groovy.json.JsonBuilder

class ImagesWebService {

    def grailsApplication

    private String getServiceUrl() {
        def url = grailsApplication.config.ala.image.service.url ?: "http://devt.ala.org.au:8080/ala-images"
        if (!url.endsWith("/")) {
            url += "/"
        }
        return url
    }

    public Map uploadImages(List<Map> images) {
        def url = "${serviceUrl}ws/uploadImagesFromUrls"
        def results = postJSON(url, [images: images])
        if (results && results.status == 200) {
            return results.content.results
        } else {
            throw new RuntimeException("Upload failed! ${results}")
        }
    }

    public Map getImageInfo(List images) {

        def url = "${serviceUrl}ws/findImagesByOriginalFilename"
        def filenameList = images*.sourceUrl

        def results = postJSON(url, [filenames: filenameList])

        if (results.status == 200) {
            def resultsMap = results.content.results
            def map = [:]
            images.each { img ->

                def imgResults = resultsMap[img.sourceUrl]
                if (imgResults && imgResults.count > 0) {
                    map[img.sourceUrl] = imgResults.images[0]
                }
            }
            return map
        } else {
            println results
        }
        return null
    }

    def static getHeadStatus(String url) {
        // url = URLEncoder.encode(url, "utf-8")
        RESTClient c = new RESTClient(url)
        try {
            HttpResponseDecorator response = c.head(path: '')
            return response.status
        } catch (Exception ex) {
            System.err.println(url)
            return ex.response?.status ?: 0
        }
    }

    static def postJSON(url, Map params) {
        def result = [:]
        HTTPBuilder builder = new HTTPBuilder(url)
        builder.request(Method.POST, ContentType.JSON) { request ->

            requestContentType : 'application/JSON'
            body = new JsonBuilder(params).toString()

            response.success = {resp, message ->
                result.status = resp.status
                result.content = message
            }

            response.failure = {resp ->
                result.status = resp.status
                result.error = "Error POSTing to ${url}"
            }

        }
        result
    }

}
