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
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintLayout}"

        object Ktx {
            const val core = "androidx.core:core-ktx:${Versions.AndroidX.Ktx.core}"
            const val liveData =
                "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.AndroidX.Ktx.liveData}"
            const val room = "androidx.room:room-ktx:${Versions.AndroidX.roomVersion}"
        }

        object Room {
            const val runtime = "androidx.room:room-runtime:${Versions.AndroidX.roomVersion}"
            const val compiler = "androidx.room:room-compiler:${Versions.AndroidX.roomVersion}"
        }

        object Lifecycle {
            const val extensions =
                "androidx.lifecycle:lifecycle-extensions:${Versions.AndroidX.lifecycleVersion}"
            const val compiler =
                "androidx.lifecycle:lifecycle-compiler:${Versions.AndroidX.lifecycleVersion}"
        }

        object Test {
            const val runner = "androidx.test:runner:${Versions.AndroidX.Test.ext}"
            const val espresso =
                "androidx.test.espresso:espresso-core:${Versions.AndroidX.Test.espresso}"
        }
    }

    object Google {
        const val ossLicensesPlugin =
            "com.google.android.gms:oss-licenses-plugin:${Versions.Google.ossLicensesPlugin}"
        const val ossLicenses =
            "com.google.android.gms:play-services-oss-licenses:${Versions.Google.ossLicenses}"
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