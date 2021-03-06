package org.liberalist.website.domain

import org.liberalist.website.domain.contract.FilesContract
import org.liberalist.website.domain.contract.FilesDelegate
import java.nio.file.Paths

fun main(args: Array<String>) {
    val files: FilesContract = FilesDelegate
    val basePath = Paths.get("content")
    val contentScanner: ContentScanner = ContentScannerImpl(files, basePath)
    val sources = contentScanner.findSources()
    sources.toLines().forEach(::println)
}
