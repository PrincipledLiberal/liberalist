package org.liberalist.website.domain

import org.liberalist.website.domain.tree.Branch

interface ModelFactory {
    fun createModel(tree: Branch<String>, titles: Map<String, String>): Model
    data class Tab(val name: String,
                   val title: String,
                   val selected: Boolean?,
                   val parent: Boolean?)
    data class Page(val tabBars: List<List<Tab>>, val content: String)
    data class Model(val home: String, val pages: Map<String, Page>)
}
