package org.liberalist.website.domain

import org.liberalist.website.domain.tree.Tree
import java.nio.file.Path

interface ContentScanner {
    fun findSources(): Tree<Path>
}