package org.liberalist.website.domain

class DependencyInjectionCommandLine(args: Array<String>) {
    private val createDeployLocal: () -> Runnable = {
        DependencyInjectionLocal().deploySiteRunner
    }
    private val createDeployProduction: (String, String) -> Runnable = { awsAccessKeyId, awsSecretKey ->
        DependencyInjectionProduction(awsAccessKeyId, awsSecretKey).deploySiteRunner
    }
    private val emitLine: (String) -> Unit = ::println
    val runner: Runnable = CommandLineArgumentsRunner(
            args,
            emitLine,
            createDeployLocal,
            createDeployProduction)
}
