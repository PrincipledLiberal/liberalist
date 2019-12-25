package org.liberalist.website.domain

data class Tab(val tabPath: TabPath, val subTabs: List<Tab> = emptyList()) {
    val name: String = tabPath.name
    val href: String = tabPath.href
}
