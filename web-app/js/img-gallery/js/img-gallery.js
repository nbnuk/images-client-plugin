/**
 *
 * @param containerId
 * @param options
 * @returns public methods
 * @constructor
 */
var GalleryWidget = function(containerId, options) {

    var defaultOptions = {
        width: '100%',
        height: '100%',
        fade: true,
        arrows: false,
        buttons: false,
        fullScreen: false,
        shuffle: false,
        thumbnailArrows: true,
        autoplay: false
    };
    var carousel;

    var init = function() {
        carousel = $('#' + containerId).sliderPro($.extend(defaultOptions, options));
    };

    init();
    return {
        destroy: function() {
            imgvwr.removeCurrentImage();
            $('#' + containerId).empty();
            carousel.update();
            carousel.destroy();
            //$('#carousel').sliderPro('update');
            //$('#carousel').sliderPro('destroy');
        }
    }

};