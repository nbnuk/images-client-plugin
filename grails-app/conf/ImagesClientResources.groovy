modules = {

    leaflet {
        dependsOn 'jquery'
        resource url: [plugin: "images-client-plugin", dir: 'js/leaflet', file: 'leaflet.css']
        resource url: [plugin: "images-client-plugin", dir: 'js/leaflet', file: 'Control.FullScreen.css']
        resource url: [plugin: "images-client-plugin", dir: 'js/leaflet', file: 'leaflet.js']
        resource url: [plugin: "images-client-plugin", dir: 'js/leaflet', file: 'Control.FullScreen.js']
        resource url: [plugin: "images-client-plugin", dir: 'js/leaflet', file: 'leaflet.measure.js']
        resource url: [plugin: "images-client-plugin", dir: 'js/leaflet', file: 'leaflet.measure.css']
    }

    leaflet_draw {
        dependsOn 'leaflet'
        resource url: [plugin: "images-client-plugin", dir: 'js/leaflet.draw', file: 'leaflet.draw.css']
        resource url: [plugin: "images-client-plugin", dir: 'js/leaflet.draw', file: 'leaflet.draw.js']
    }

    audiojs {
        dependsOn "jquery"
        resource url: [plugin: "images-client-plugin", dir: 'js/audiojs', file: 'audio.min.js']
    }

    viewer {
        dependsOn 'jquery', 'leaflet', 'leaflet_draw', 'font-awesome'
        resource url: [plugin: "images-client-plugin", dir: 'js', file: 'ala-image-viewer.js']
    }
}