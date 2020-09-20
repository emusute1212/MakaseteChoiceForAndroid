import ext.child
import ext.fileWriter
import ext.newLine

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("com.google.android.gms.oss-licenses-plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion = Versions.buildTools

    defaultConfig {
        applicationId = Versions.applicationId
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
        versionCode = Versions.MakaseteChoice.code
        versionName = Versions.MakaseteChoice.name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            applicationIdSuffix = ".debug"
        }
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    dataBinding {
        isEnabled = true
    }

}

dependencies {
    // Kotlin
    implementation(Dependencies.Kotlin.stdLib)

    // AppCompat
    implementation(Dependencies.AndroidX.appCompat)

    // Lifecycle
    implementation(Dependencies.AndroidX.Lifecycle.extensions)
    kapt(Dependencies.AndroidX.Lifecycle.compiler)

    // Room
    implementation(Dependencies.AndroidX.Room.runtime)
    kapt(Dependencies.AndroidX.Room.compiler)

    // Ktx
    implementation(Dependencies.AndroidX.Ktx.core)
    implementation(Dependencies.AndroidX.Ktx.liveData)
    implementation(Dependencies.AndroidX.Ktx.room)

    // Google
    implementation(Dependencies.Google.ossLicenses)
    implementation(Dependencies.Google.Firebase.crashlytics)

    // Ui
    implementation(Dependencies.AndroidX.constraintLayout)

    // Navigation
    implementation(Dependencies.JetPack.Navigation.core)
    implementation(Dependencies.JetPack.Navigation.ktx)

    // Dagger2
    implementation(Dependencies.ThirdParty.Dagger.android)
    implementation(Dependencies.ThirdParty.Dagger.androidSupport)
    kapt(Dependencies.ThirdParty.Dagger.androidProcessor)
    kapt(Dependencies.ThirdParty.Dagger.compiler)

    // Groupie
    implementation(Dependencies.ThirdParty.Groupie.core)
    implementation(Dependencies.ThirdParty.Groupie.kotlinAndroidExtension)
    implementation(Dependencies.ThirdParty.Groupie.viewBinding)

    // Test
    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.AndroidX.Test.runner)
    androidTestImplementation(Dependencies.AndroidX.Test.espresso)
}


// 自動登録されないOSSライセンスを追加するタスク
tasks.register("addOssLicenseTask") {
    mustRunAfter(tasks.findByName("generateLicenses"))
    doLast {
        project.addOssLicense(
            "Noto Sans JP",
            "https://fonts.google.com/specimen/Noto+Sans+JP#license"
        )
        project.addOssLicense(
            "Font Awesome",
            """
                Font Awesome Free License
                -------------------------

                Font Awesome Free is free, open source, and GPL friendly. You can use it for
                commercial projects, open source projects, or really almost whatever you want.
                Full Font Awesome Free license: https://fontawesome.com/license/free.

                # Icons: CC BY 4.0 License (https://creativecommons.org/licenses/by/4.0/)
                In the Font Awesome Free download, the CC BY 4.0 license applies to all icons
                packaged as SVG and JS file types.

                # Fonts: SIL OFL 1.1 License (https://scripts.sil.org/OFL)
                In the Font Awesome Free download, the SIL OFL license applies to all icons
                packaged as web and desktop font files.

                # Code: MIT License (https://opensource.org/licenses/MIT)
                In the Font Awesome Free download, the MIT license applies to all non-font and
                non-icon files.

                # Attribution
                Attribution is required by MIT, SIL OFL, and CC BY licenses. Downloaded Font
                Awesome Free files already contain embedded comments with sufficient
                attribution, so you shouldn't need to do anything additional when using these
                files normally.

                We've kept attribution comments terse, so we ask that you do not actively work
                to remove them from files, especially code. They're a great way for folks to
                learn about Font Awesome.

                # Brand Icons
                All brand icons are trademarks of their respective owners. The use of these
                trademarks does not indicate endorsement of the trademark holder by Font
                Awesome, nor vice versa. **Please do not use brand logos for any purpose except
                to represent the company, product, or service to which they refer.**
            """.trimIndent()
        )
    }
}

fun Project.addOssLicense(libName: String, licenseContent: String) {
    val outputDir = File(buildDir, "generated/third_party_licenses")
        .child("res")
        .child("raw")
    // ライセンスファイル
    val licensesFile = File(outputDir, "third_party_licenses")
    // ライセンスファイルへの書き込み前に現在の位置を保持
    val start = licensesFile.length()

    // ライセンスファイルへ書き込み
    licensesFile.fileWriter(true).use {
        it.write(licenseContent)
        it.newLine()
    }

    // ライセンスメタデータファイルに書き込み
    File(outputDir, "third_party_license_metadata").fileWriter(true).use {
        it.write("${start}:${licenseContent.length} $libName")
        it.newLine()
    }
}

// preBuild前にライセンス情報を追加する
checkNotNull(tasks.findByPath(":app:preBuild"))
    .dependsOn("addOssLicenseTask")