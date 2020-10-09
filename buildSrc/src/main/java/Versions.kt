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
        const val lifecycleVersion = "2.2.0"
        const val roomVersion = "2.2.5"

        object Ktx {
            const val core = "1.0.0"
            const val liveData = "2.2.0"
        }

        object Test {
            const val ext = "1.1.1"
            const val espresso = "3.2.0"
        }
    }

    object Google {
        const val googleService = "4.3.3"
        const val ossLicensesPlugin = "0.10.2"
        const val ossLicenses = "17.0.0"

        object Firebase {
            const val crashlyticsGradle = "2.2.1"
            const val crashlytics = "17.2.1"
        }
    }

    object JetPack {
        const val navigation = "2.2.2"
    }

    object ThirdParty {
        const val dagger = "2.27"
        const val groupie = "2.8.1"
    }

    object Test {
        const val jUnit = "4.12"
    }

    object MakaseteChoice {
        private val version = Version(1, 0, 3, 0)
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