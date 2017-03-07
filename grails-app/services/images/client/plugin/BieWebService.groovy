package images.client.plugin

import grails.converters.JSON
import groovyx.net.http.HTTPBuilder
import org.springframework.http.HttpStatus

/**
 * Created by koh032 on 2/03/2017.
 */
class BieWebService {
    def grailsApplication

    private String getServiceUrl() {
        def url = grailsApplication.config.bieService.baseUrl
        if (!url.endsWith("/")) {
            url += "/"
        }
        return url
    }

    def updateBieIndex(def guidImageList) {
        def response

        List<Map> list = []
        guidImageList.each{
            list.push ([guid: it.value])
        }
        def http = new HTTPBuilder(getServiceUrl() + "updateImages")
        http.setHeaders(["Authorization": grailsApplication.config.bieApiKey])
        def jsonBody = (list as JSON).toString()
        try {
            def result =  http.post(body: jsonBody, requestContentType:groovyx.net.http.ContentType.JSON)
            def status
            if (result && result.success) {
                status = HttpStatus.OK
                log.info("Successfully updated Bie: " + jsonBody)
            } else {
                status = HttpStatus.INTERNAL_SERVER_ERROR
                log.info("Fail to update Bie with status ${status} with the param: " + jsonBody)
            }
            response = [status: status, text: result.message]

        } catch(ex) {
            log.error("Unable to obtain species details from BIE - " + ex.getMessage(), ex)
            response = [status: 500, text: ex.getMessage()]
        }
        response
    }

}
