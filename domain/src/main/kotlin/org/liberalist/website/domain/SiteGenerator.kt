package org.liberalist.website.domain

import org.liberalist.website.domain.contract.FilesContract
import java.nio.file.Path

class SiteGenerator(val basePath: Path,
                    val files: FilesContract,
                    val markdownToHtmlTransformer: MarkdownToHtmlTransformer) {
    fun generate() {
        val markdownSources = findMarkdownSources()
        markdownSources.forEach(::convertMarkdownToHtml)
    }

    private fun findMarkdownSources(): List<Path> = TODO()
    private fun convertMarkdownToHtml(path: Path) {
        TODO()
    }
}
