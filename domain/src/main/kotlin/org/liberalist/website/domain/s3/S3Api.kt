package org.liberalist.website.domain.s3

import java.nio.file.Path

interface S3Api {
    fun ensureBucketExists()
    fun uploadFile(name: String, path: Path)
    fun enableWebsiteHosting()
}
