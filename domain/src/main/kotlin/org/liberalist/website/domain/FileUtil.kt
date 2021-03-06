package org.liberalist.website.domain

import org.liberalist.website.domain.contract.FilesContract
import java.nio.file.Path

object FileUtil {
    fun createIsDirectoryFunction(files: FilesContract): (Path) -> Boolean = { path: Path ->
        files.isDirectory(path)
    }

    fun createHasExtensionFunction(vararg extensions: String): (Path) -> Boolean = { path: Path ->
        var result = false
        for (extension in extensions) {
            if (path.fileName.toString().endsWith(extension)) {
                result = true
                break
            }
        }
        result
    }
}
