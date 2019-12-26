package org.liberalist.website.domain

import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.liberalist.website.domain.credentials.CredentialsProvider
import org.liberalist.website.domain.s3.S3ApiClient
import org.liberalist.website.domain.secrets.SecretsImpl

class DependencyInjectionProduction(awsAccessKeyId: String, awsSecretKey: String) : DependencyInjection() {
    private val secrets = SecretsImpl(
            awsAccessKeyId = awsAccessKeyId,
            awsSecretKey = awsSecretKey)
    private val credentialsProvider =
            CredentialsProvider(secrets.awsAccessKeyId, secrets.awsSecretKey)
    private val bucketName = "liberalist.org"
    private val region = Regions.US_EAST_1
    private val s3Client = AmazonS3ClientBuilder.standard().withCredentials(credentialsProvider).withRegion(region).build()
    private val s3Api = S3ApiClient(s3Client, bucketName, emitLine)
    override val uploader: Uploader = UploaderS3(s3Api, generatedDir, files)
    override val deploySiteRunner: Runnable = DeploySite(
            contentScanner,
            htmlGenerator,
            staticContentCopier,
            modelGenerator,
            uploader)
}
