package org.liberalist.website.domain

import org.liberalist.website.domain.compare.NaturalSort
import java.nio.file.Path

object PathNaturalSort : Comparator<Path> {
    override fun compare(o1: Path?, o2: Path?): Int = NaturalSort.compare(o1.toString(), o2.toString())
}