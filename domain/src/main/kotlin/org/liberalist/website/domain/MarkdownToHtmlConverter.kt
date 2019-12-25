package org.liberalist.website.domain

interface MarkdownToHtmlConverter {
    fun markdownToHtml(markdown: String): TitleAndHtml
}
