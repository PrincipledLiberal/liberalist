package org.liberalist.website.domain.test

import org.liberalist.website.domain.compare.ListDifference
import org.liberalist.website.domain.io.IoUtil
import java.nio.charset.StandardCharsets
import kotlin.test.assertTrue

object TestUtil {
    fun assertMultilineEquals(expected: String, actual: String) {
        val difference = ListDifference.compare(
                "expected", expected,
                "actual  ", actual)
        assertTrue(difference.isSame, difference.messageLines.joinToString("\n"))
    }

    fun loadResource(name: String): String {
        val inputStream = this.javaClass.getResourceAsStream(name)
                ?: throw RuntimeException("Resource named '$name' not found")
        return IoUtil.inputStreamToString(inputStream, StandardCharsets.UTF_8)
    }

}