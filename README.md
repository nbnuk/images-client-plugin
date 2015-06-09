### images-client-plugin   [![Build Status](https://travis-ci.org/AtlasOfLivingAustralia/images-client-plugin.svg?branch=master)](https://travis-ci.org/AtlasOfLivingAustralia/images-client-plugin)
images-client-plugin
====================

This plugin provides a reusable image viewer component based on [Leaflet](http://leafletjs.com/).

## Usage

runtime ":images-client-plugin:0.6"

The `image-viewer` resource module needs to be added to the page where we want to use the viewer. Eg using the Resources plugin:

```
<r:require modules="image-viewer"/>
```

This will create a viewer instance in the document global scope called `imgvwr`.

To display a tiled image we need the `imageId` as per the [images.ala.org.au](http://images.ala.org.au) service and the id of the `<div>` element that will contain the viewer as parameters of the `viewImage` method. Eg:

```
imgvwr.viewImage($("#viewerContainerId"), imageId, {custom options...});
```

### Viewer options
| Option  | Default value | Description
| ------- | ------------- | -----------
| auxDataUrl | ''  | TODO
| auxDataTitle | ''  | Default value is 'View more information about this image'... TODO
| initialZoom | 'auto' | number with the initial zoom level. When set to 'auto' the most suitable zoom level for the viewer container size will be automatically calculated.
| addDownloadButton | true | Shows the image download button control.

### Viewer public methods



## Thumbnails gallery component


## Changelog
* ** version 0.6** (TODO)
  * Added reusable thumbnail image gallery component based a customized implementation of the [SliderPro](https://github.com/bqworks/slider-pro/) component to reuse just the thumbnail gallery bit. This component is responsive, accesible through keyboard and support the swipe mobile events.
  * Added some image gallery related options for the existing leaflet based viewer.

* **version 0.5.3** (02/06/2005)
  * Bugfix release

* **version 0.5** (28/05/2005)
  * Transfer of the subimaging tools to this plugin
  * Added [leaflet.loading](https://github.com/ebrelsford/Leaflet.loading) control.

* **version 0.4** (20/05/2015)
  * Optional parameter `initialZoom` default value is now 'auto'. This means that the component will adjust the optimum zoom level so the image fits inside the image viewer container.
  * Removed unnecessary grails plugin dependency with ala-bootstrap2.
