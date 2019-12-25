package org.liberalist.website.domain

import org.liberalist.website.domain.tree.Tree

interface ModelGenerator {
    fun generateModel(htmlGeneratorResult: Tree<HtmlConversion>)
}
