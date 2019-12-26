package org.liberalist.website.domain

class DeploySite(private val contentScanner: ContentScanner,
                 private val htmlGenerator: HtmlGenerator,
                 private val staticContentCopier: StaticContentCopier,
                 private val modelGenerator: ModelGenerator,
                 private val uploader: Uploader,
                 private val deployToLocation: String) : Runnable {
    override fun run() {
        val sources = contentScanner.findSources()
        val htmlGeneratorResult = htmlGenerator.generateHtml(sources)
        staticContentCopier.copyStaticContent()
        modelGenerator.generateModel(htmlGeneratorResult)
        if ("production".equals(deployToLocation, ignoreCase = true)) {
            uploader.upload()
        }
    }
}
