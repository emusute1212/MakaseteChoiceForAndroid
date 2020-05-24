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

    object Test {
        const val jUnit = "junit:junit:${Versions.Test.jUnit}"
    }
}