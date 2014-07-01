modules = {

    fontawesome {
        resource url: "http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
    }

    leaflet {
        dependsOn 'jquery'
        resource url: 'js/leaflet/leaflet.css'
        resource url: 'js/leaflet/Control.FullScreen.css'
        resource url: 'js/leaflet/leaflet.js'
        resource url: 'js/leaflet/Control.FullScreen.js'
        resource url: 'js/leaflet/leaflet.measure.js'
        resource url: 'js/leaflet/leaflet.measure.css'
    }

    leaflet_draw {
        dependsOn 'leaflet'
        resource 'js/leaflet.draw/leaflet.draw.css'
        resource 'js/leaflet.draw/leaflet.draw.js'

    }

    audiojs {
        dependsOn "jquery"
        resource url: 'js/audiojs/audio.min.js'
    }

    viewer {
        dependsOn "jquery, leaflet, leaflet_draw, fontawesome"
        resource url: 'js/ala-image-viewer.js'
    }
}