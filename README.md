### images-client-plugin   [![Build Status](https://travis-ci.org/AtlasOfLivingAustralia/images-client-plugin.svg?branch=master)](https://travis-ci.org/AtlasOfLivingAustralia/images-client-plugin)
images-client-plugin
====================

This plugin provides a reusabe image viewer component based on [Leaflet](http://leafletjs.com/).

## Usage

runtime ":images-client-plugin:0.4"

## Changelog

* **version 0.4** (20/05/2015)
  * Optional parameter `initialZoom` default value is now 'auto'. This means that the component will adjust the optimum zoom level so the image fits inside the image viewer container.
  * Removed unnecessary grails plugin dependency with ala-bootstrap2.
