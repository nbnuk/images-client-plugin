<form class="form-horizontal">
    <div class="well">
        The file must contain column headings, and must have at least one column called <code>imageUrl</code> which contains a url to an image. Data in other columns will be stored as metadata against the image.
    </div>
    <div class="control-group">
        <label class="control-label" for="imagefile">Select a file</label>
        <div class="controls">
            <input type="file" name="imagefile" id="imagefile"/>
        </div>
    </div>
    <div class="control-group">
        <div class="controls">
            <button type="button" class="btn" id="btnCancelCSVFileUpload">Cancel</button>
            <button type="button" class="btn btn-primary">Upload</button>
        </div>
    </div>
</form>

<script>

    $("#btnCancelCSVFileUpload").click(function(e) {
        e.preventDefault();
        imglib.hideModal();
    });

</script>