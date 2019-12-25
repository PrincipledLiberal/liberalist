package org.liberalist.website.domain

import org.liberalist.website.domain.tree.Tree
import java.nio.file.Path

interface HtmlGenerator {
    fun generateHtml(sources: Tree<Path>): Tree<HtmlConversion>
}
