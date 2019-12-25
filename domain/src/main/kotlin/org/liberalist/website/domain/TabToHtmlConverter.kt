package org.liberalist.website.domain

interface TabToHtmlConverter {
    fun tabToTopLevelHtml(title: String, tab: Tab): String
}
