package images.client.plugin

import grails.plugins.Plugin

class ImagesClientPluginGrailsPlugin extends Plugin {
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "3.2.11 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    def title = "ALA Image Service Client Plugin" // Headline display name of the plugin
    def author = "David Baird"
    def authorEmail = "david.baird.ala@gmail.com    "
    def description = '''\
    A collection of useful utilities and components for use by clients of the ALA Image Service
'''

    // URL to the plugin's documentation
    def documentation = "https://github.com/AtlasOfLivingAustralia/images-client-plugin/tree/master"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
    def license = "MPL2"

    // Details of company behind the plugin (if there is one)
    def organization = [ name: "NBN Atlas", url: "https://www.nbnatlas.org/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
    def issueManagement = [ system: "GIT", url: "https://github.com/AtlasOfLivingAustralia/images-client-plugin/issues" ]

    // Online location of the plugin's browseable source code.
    def scm = [ url: "https://github.com/AtlasOfLivingAustralia/images-client-plugin" ]

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
    }

    Closure doWithSpring() {
        { ->
            println("ImagesClientPluginGrailsPlugin starting")
        }
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { ctx ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    def onShutdown = { event ->
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}