package org.liberalist.website.domain

import org.liberalist.website.domain.contract.FilesContract
import org.liberalist.website.domain.contract.FilesDelegate
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.nio.file.Path
import java.nio.file.Paths

abstract class DependencyInjection {
    private val baseDir: Path = Paths.get(".")
    private val sourceDir: Path = baseDir.resolve("content")
    protected val generatedDir: Path = baseDir.resolve(Paths.get("build", "html"))
    private val sourceStaticDir: Path = baseDir.resolve("static")
    protected val emitLine: (String) -> Unit = ::println
    protected val files: FilesContract = MonitoredFiles(FilesDelegate, emitLine)
    private val charset: Charset = StandardCharsets.UTF_8
    private val markdownToHtmlConverter: MarkdownToHtmlConverter = FlexmarkConverter
    protected val staticContentCopier: StaticContentCopier = StaticContentCopierImpl(
            files, sourceStaticDir, generatedDir)
    protected val contentScanner: ContentScanner = ContentScannerImpl(files, sourceDir)
    protected val htmlGenerator: HtmlGenerator = HtmlGeneratorImpl(
            sourceDir, generatedDir, files, markdownToHtmlConverter, charset)
    private val modelFactory: ModelFactory = ModelFactoryImpl()
    protected val modelGenerator: ModelGenerator = ModelGeneratorImpl(
            generatedDir,
            files,
            charset,
            modelFactory)
    protected abstract val uploader: Uploader
    abstract val deploySiteRunner: Runnable
}
