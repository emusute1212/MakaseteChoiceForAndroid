object Versions {
    const val applicationId = "io.github.emusute1212.makasetechoice"
    const val compileSdk = 29
    const val buildTools = "29.0.2"
    const val targetSdk = 29
    const val minSdk = 23

    object Gradle {
        const val plugin = "3.6.3"
        const val kotlinPlugin = "1.3.71"
    }

    object Kotlin {
        const val stdLib = "1.3.71"
    }

    object AndroidX {
        const val appCompat = "1.1.0"
        const val constraintLayout = "1.1.3"

        object Ktx {
            const val core = "1.0.0"
        }

        object Test {
            const val ext = "1.1.1"
            const val espresso = "3.2.0"
        }
    }

    object Test {
        const val jUnit = "4.12"
    }

    object MakaseteChoice {
        private val version = Version(0, 1, 0, 0)
        val code = version.getVersionCode()
        val name = version.getVersionName()
    }

    private fun Version.getVersionName(): String {
        return "$major.$minor.$patch.$rc"
    }

    private fun Version.getVersionCode(): Int {
        return ((1 + major) * 10000 + minor * 100 + patch) * 100 + rc
    }

    private data class Version(
        val major: Int,
        val minor: Int,
        val patch: Int,
        val rc: Int
    )
}