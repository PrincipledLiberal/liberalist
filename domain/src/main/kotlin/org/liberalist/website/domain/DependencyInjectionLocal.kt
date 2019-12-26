package org.liberalist.website.domain

class DependencyInjectionLocal : DependencyInjection() {
    override val uploader: Uploader = UploaderNoOperation()
    override val deploySiteRunner: Runnable = DeploySite(
            contentScanner,
            htmlGenerator,
            staticContentCopier,
            modelGenerator,
            uploader)
}
