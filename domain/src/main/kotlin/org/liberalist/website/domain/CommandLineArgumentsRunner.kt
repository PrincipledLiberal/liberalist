package org.liberalist.website.domain

class CommandLineArgumentsRunner(private val args: Array<String>,
                                 private val emitLine: (String) -> Unit,
                                 private val createDeployLocal: () -> Runnable,
                                 private val createDeployProduction: (String, String) -> Runnable) : Runnable {
    override fun run() {
        when (args.size) {
            0 -> {
                createDeployLocal().run()
            }
            2 -> {
                val awsAccessKeyId = args[0]
                val awsSecretKey = args[1]
                createDeployProduction(awsAccessKeyId, awsSecretKey).run()
            }
            else -> {
                emitLine("to deploy locally, send in 0 arguments")
                emitLine("to deploy to production, send in 2 arguments:")
                emitLine("  the amazon web services access key id")
                emitLine("  the amazon web services secret key")

            }
        }
        if (args.size == 2) {
        } else {
        }
    }
}
