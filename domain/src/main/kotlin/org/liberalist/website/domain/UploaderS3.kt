package org.liberalist.website.domain

import org.liberalist.website.domain.contract.FilesContract
import org.liberalist.website.domain.s3.S3Api
import java.io.IOException
import java.nio.file.FileVisitResult
import java.nio.file.FileVisitor
import java.nio.file.Path
import java.nio.file.attribute.BasicFileAttributes

class UploaderS3(private val s3Api: S3Api,
                 private val generated: Path,
                 private val files: FilesContract) : Uploader {
    override fun upload() {
        s3Api.ensureBucketExists()
        val visitor = object : FileVisitor<Path> {
            override fun postVisitDirectory(dir: Path?, exc: IOException?): FileVisitResult =
                    FileVisitResult.CONTINUE

            override fun visitFile(file: Path?, attrs: BasicFileAttributes?): FileVisitResult {
                uploadFile(file!!)
                return FileVisitResult.CONTINUE
            }

            override fun visitFileFailed(file: Path?, exc: IOException?): FileVisitResult =
                    FileVisitResult.CONTINUE

            override fun preVisitDirectory(dir: Path?, attrs: BasicFileAttributes?): FileVisitResult =
                    FileVisitResult.CONTINUE
        }
        files.walkFileTree(generated, visitor)
    }

    private fun uploadFile(file: Path) {
        val name = generated.relativize(file).toString()
        s3Api.uploadFile(name, file)
    }
}
