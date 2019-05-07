<div class="form-horizontal">
    <form>
        <label for="description">
            Description
        </label>
        <input id="description" type="text" class="form-control input-xlarge" name="description" value=""/>
    </form>

    <div class="control-group">
        <div class="controls">
            <btn class="btn btn-default" id="btnCancelSubimage">Cancel</btn>
            <btn class="btn btn-primary" id="btnCreateSubimage2">Create subimage</btn>
        </div>
    </div>
    <script type="text/javascript">

        $("#btnCancelSubimage").click(function(e) {
            e.preventDefault();
            imgvwr.hideModal();
        });

        $("#btnCreateSubimage2").click(function(e) {
            e.preventDefault();
            var url = imgvwr.getImageServiceBaseUrl() + "/ws/createSubimage?id=${params.id}&x=${params.x}&y=${params.y}&width=${params.width}&height=${params.height}&userId=${userId}&description=" + encodeURIComponent($('#description').val());
            $.ajax(url).done(function(results) {
                if (results.success) {
                    imgvwr.hideModal();
                    imgvwr.showSubimages();
                } else {
                    alert("Failed to create sub image: " + results.message);
                }
            });
        });
    </script>
</div>

