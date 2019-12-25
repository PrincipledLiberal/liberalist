package org.liberalist.website.domain

import java.nio.file.Path

interface MarkdownToHtmlTransformer {
    fun transform(file: Path): String
}
