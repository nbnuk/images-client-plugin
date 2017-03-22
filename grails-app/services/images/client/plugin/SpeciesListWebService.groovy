package images.client.plugin

import grails.converters.JSON
import grails.web.JSONBuilder
import groovy.json.JsonSlurper
import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.methods.PostMethod
import org.apache.commons.httpclient.methods.StringRequestEntity
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.CacheEvict

class SpeciesListWebService {

    def grailsApplication

    private String getServiceUrl() {
        def url = grailsApplication.config.speciesList.baseURL
        if (!url.endsWith("/")) {
            url += "/"
        }
        return url
    }

    private getSpeciesListDruid() {
        return grailsApplication.config.speciesList.preferredSpeciesListDruid ? grailsApplication.config.speciesList.preferredSpeciesListDruid : "dr4778"
    }

    private getSpeciesListName() {
        return grailsApplication.config.speciesList.preferredListName ? grailsApplication.config.speciesList.preferredListName : "ALA Preferred Species Images"
    }

    @Cacheable("speciesListKvp")
    def getPreferredImageSpeciesList() {
        String druid = getSpeciesListDruid()
        def url = getServiceUrl() + "ws/speciesListItemKvp/" + druid
        def results = getJSON(url)
        return results
    }

    @CacheEvict(value="speciesListKvp", allEntries=true)
    def saveImageToSpeciesList(def scientificName, def imageId, cookie) {

        String druid = getSpeciesListDruid ()
        String listNameVal = getSpeciesListName ()

        def url = getServiceUrl() + "ws/speciesList/" + druid

        def kvpValues = [[key: "imageId", value: imageId]]

        def listMap = [
                itemName: scientificName, kvpValues: kvpValues
        ]

        def builder = new JSONBuilder()
        def jsonBody = builder.build {
            listName = listNameVal
            listItems = [listMap]
            replaceList = false
        }
        def response = doPostJSON(url, jsonBody, cookie)
        def result = [status: response.status, text: response.message, data: response.data]
        return result
    }

    def doPostJSON(String url, def jsonBody, def cookie) {

        def response = [:]
        try {
            HttpClient client = new HttpClient();
            PostMethod post = new PostMethod(url);
            post.setRequestHeader('cookie',cookie)
            StringRequestEntity requestEntity = new StringRequestEntity(jsonBody.toString(), "application/json", "utf-8")
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
            log.error(url)
            log.error(ex.message)
            return null
        }
    }

}
