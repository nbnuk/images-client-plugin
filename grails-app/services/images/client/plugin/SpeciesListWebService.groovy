package images.client.plugin

import grails.converters.JSON
import groovy.json.JsonSlurper
import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.methods.PostMethod
import org.apache.commons.httpclient.methods.StringRequestEntity

class SpeciesListWebService {

    def grailsApplication

    private String getServiceUrl() {
        def url = grailsApplication.config.speciesList.baseURL
        if (!url.endsWith("/")) {
            url += "/"
        }
        return url
    }

    def saveImageToSpeciesList(def scientificName, def imageId) {
        def url = getServiceUrl() + "ws/speciesListItem/preferredSpeciesImage"
        def params = [:]
        params.scientificName = scientificName
        params.imageId = imageId
        def results = doPostJSON(url, params)
        return results
    }

    def getPreferredImageSpeciesList() {
        def url = getServiceUrl() + "ws/speciesListItem/getPreferredSpeciesImage"
        def results = getJSON(url)
        return results
    }

    def doPostJSON(String url, Map postBody) {

        def response = [:]
        try {
            HttpClient client = new HttpClient();
            PostMethod post = new PostMethod(url);

            StringRequestEntity requestEntity = new StringRequestEntity((postBody as JSON).toString(), "application/json", "utf-8")

            post.setRequestEntity(requestEntity)
            client.executeMethod(post);
            String responseStr = post.getResponseBodyAsString();
            response = JSON.parse(responseStr)

        } catch (SocketTimeoutException e) {
            def error = [text: "Timed out calling web service. URL= ${url}."]
            log.error(error, e)
            response = [text: error, status: 500 ]
        } catch (Exception e) {
            def error = [text: "Failed calling web service. ${e.getMessage()} URL= ${url}."]
            log.error(error, e)
            response = [text: error, status: 500]
        }
        return response
    }

    def getJSON(String url) {
        try {
            def u = new URL(url);
            def text = u.text
            return new JsonSlurper().parseText(text)
        } catch (Exception ex) {
            System.err.println(url)
            System.err.println(ex.message)
            return null
        }
    }

}
