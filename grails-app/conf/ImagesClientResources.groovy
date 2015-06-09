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

    'leaflet-draw' {
        dependsOn 'leaflet'
        resource url: [plugin: "images-client-plugin", dir: 'js/leaflet.draw', file: 'leaflet.draw.css']
        resource url: [plugin: "images-client-plugin", dir: 'js/leaflet.draw', file: 'leaflet.draw.js']
    }

    'leaflet-loading' {
        dependsOn 'leaflet'
        resource url: [plugin: "images-client-plugin", dir: 'js/leaflet.loading', file: 'Control.Loading.css']
        resource url: [plugin: "images-client-plugin", dir: 'js/leaflet.loading', file: 'Control.Loading.js']
    }

    audiojs {
        dependsOn "jquery"
        resource url: [plugin: "images-client-plugin", dir: 'js/audiojs', file: 'audio.min.js']
    }

    'image-viewer' {
        dependsOn 'jquery', 'bootstrap', 'leaflet', 'leaflet-draw', 'leaflet-loading', 'font-awesome'
        resource url: [plugin: "images-client-plugin", dir: 'js', file: 'ala-image-viewer.js']
    }

    'img-gallery' {
        dependsOn 'image-viewer'
        resource url: 'js/img-gallery/lib/slider-pro/css/slider-pro.css'
        resource url: 'js/img-gallery/css/img-gallery.css'

        resource url: 'js/img-gallery/lib/slider-pro/js/jquery.sliderPro.custom.js'
        resource url: 'js/img-gallery/js/img-gallery.js'
    }
}