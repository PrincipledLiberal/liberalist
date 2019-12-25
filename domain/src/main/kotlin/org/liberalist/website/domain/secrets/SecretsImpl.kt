package org.liberalist.website.domain.secrets

class SecretsImpl(
        override val awsAccessKeyId: String,
        override val awsSecretKey: String
) : Secrets
