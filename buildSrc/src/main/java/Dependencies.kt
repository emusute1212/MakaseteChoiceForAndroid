object Dependencies {

    object Gradle {
        const val plugin = "com.android.tools.build:gradle:${Versions.Gradle.plugin}"
        const val kotlinPlugin =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Gradle.kotlinPlugin}"
    }

    object Kotlin {
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.Kotlin.stdLib}"
    }

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appCompat}"
        const val ktx = "androidx.core:core-ktx:${Versions.AndroidX.Ktx.core}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintLayout}"

        object Test {
            const val runner = "androidx.test:runner:${Versions.AndroidX.Test.ext}"
            const val espresso =
                "androidx.test.espresso:espresso-core:${Versions.AndroidX.Test.espresso}"
        }
    }

    object JetPack {
        object Navigation {
            const val core =
                "androidx.navigation:navigation-fragment-ktx:${Versions.JetPack.navigation}"
            const val ktx = "androidx.navigation:navigation-ui-ktx:${Versions.JetPack.navigation}"
        }
    }

    object ThirdParty {
        object Dagger {
            const val android = "com.google.dagger:dagger-android:${Versions.ThirdParty.dagger}"
            const val androidSupport =
                "com.google.dagger:dagger-android-support:${Versions.ThirdParty.dagger}"
            const val androidProcessor =
                "com.google.dagger:dagger-android-processor:${Versions.ThirdParty.dagger}"
            const val compiler =
                "com.google.dagger:dagger-compiler:${Versions.ThirdParty.dagger}"
        }

        object Groupie {
            const val core = "com.xwray:groupie:${Versions.ThirdParty.groupie}"
            const val kotlinAndroidExtension =
                "com.xwray:groupie-kotlin-android-extensions:${Versions.ThirdParty.groupie}"
            const val viewBinding = "com.xwray:groupie-viewbinding:${Versions.ThirdParty.groupie}"
        }
    }

    object Test {
        const val jUnit = "junit:junit:${Versions.Test.jUnit}"
    }
}