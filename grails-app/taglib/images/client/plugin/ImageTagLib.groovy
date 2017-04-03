package images.client.plugin

class ImageTagLib {

    def authService
    static namespace = 'imageTag'

    def checkAllowableEditRole = { attrs ->
        List<String> allowedRoles = grailsApplication.config.get("allowedImageEditingRoles")?.split(",")?:[]
        def currentUserRoles = authService?.getUserId() ? authService.getUserForUserId(authService.getUserId())?.roles : []
        boolean match = currentUserRoles.any {allowedRoles.contains(it)}
        out << match;
    }

}
