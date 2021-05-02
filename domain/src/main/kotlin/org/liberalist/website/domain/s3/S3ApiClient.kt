package org.liberalist.website.domain.s3

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.BucketWebsiteConfiguration
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import java.nio.file.Path

class S3ApiClient(
    private val client: AmazonS3,
    private val bucketName: String,
    private val emit: (String) -> Unit
) : S3Api {
    override fun ensureBucketExists() {
        val buckets = client.listBuckets()
        buckets.map { it.name }.forEach {
            emit("found bucket $it")
        }
        if (!buckets.any { bucket -> bucket.name == bucketName }) {
            emit("creating bucket: $bucketName")
            client.createBucket(bucketName)
        }
    }

    override fun uploadFile(name: String, path: Path) {
        val extension = getExtension(name)
        val contentType = contentTypes[extension]
        if (contentType == null) {
            emit("no content type found for extension '$extension' on file $path")
        } else {
            emit("uploading file to bucket $bucketName with contentType $contentType: $name -> $path")
            val metadata = ObjectMetadata()
            metadata.contentType = contentType
            val request = PutObjectRequest(
                bucketName,
                name,
                path.toFile()
            ).withCannedAcl(CannedAccessControlList.PublicRead).withMetadata(metadata)
            client.putObject(request)
        }
    }

    override fun enableWebsiteHosting() {
        val configuration = BucketWebsiteConfiguration("index.html", "index.html")
        client.setBucketWebsiteConfiguration(bucketName, configuration)
    }

    private fun getExtension(name: String): String {
        val dotIndex = name.lastIndexOf('.')
        return name.substring(dotIndex + 1)
    }

    companion object {
        private val contentTypes = mapOf(
            "json" to "text/json; charset=UTF-8",
            "html" to "text/html; charset=UTF-8",
            "css" to "text/css; charset=UTF-8",
            "png" to "image/png",
            "js" to "application/javascript; charset=UTF-8",
            "md" to "text/markdown",
            "mov" to "video/quicktime",
            "m4a" to "audio/mp4",
            "mp4" to "video/mp4",
            "avi" to "video/x-msvideo",
            "wmv" to "video/x-ms-wmv"
        )
    }
}
