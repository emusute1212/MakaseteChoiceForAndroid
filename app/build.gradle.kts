import java.io.File
import java.io.FileWriter

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.google.services)
    alias(libs.plugins.google.oss.licenses.plugin)
    alias(libs.plugins.hilt)
}

android {
    val shouldMakeApk = rootProject.file("upload-keystore.jks").exists()
    val version = Version(1, 0, 3, 0)
    namespace = libs.versions.namespace.get()
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = libs.versions.applicationId.get()
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = version.code
        versionName = version.name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            if (shouldMakeApk) {
                signingConfig = signingConfigs.create("release")
            }
        }
        getByName("debug") {
            applicationIdSuffix = ".debug"
        }
    }

    if (shouldMakeApk) {
        signingConfigs {
            getByName("release") {
                // https://qiita.com/hkusu/items/cadb572c979c4d729567
                storeFile = rootProject.file("upload-keystore.jks")
                storePassword = System.getenv("KEYSTORE_PASSWORD")
                keyAlias = System.getenv("KEY_ALIAS")
                keyPassword = System.getenv("KEY_PASSWORD")
            }
        }
    }
    val javaVersion = JavaVersion.VERSION_17

    compileOptions {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
    kotlinOptions {
        jvmTarget = javaVersion.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {
    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))

    // Lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // Ktx
    implementation(libs.androidx.core.ktx)

    // Google
    implementation(libs.google.oss.licenses)

    // Crashlytics
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics.ktx)

    // Ui
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Material3
    implementation(libs.androidx.material3)

    // Navigation
    implementation(libs.navigation.compose)

    // DaggerHilt
    implementation(libs.dagger.hilt)
    ksp(libs.dagger.hilt.compiler)

    // mavericks
    implementation(libs.mavericks.core)
    implementation(libs.mavericks.hilt)
    implementation(libs.mavericks.compose)
    implementation(libs.mavericks.navigation)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
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
//    layout.buildDirectory.dir("")
    val outputDir = File(buildDir, "generated/third_party_licenses")
        .child("res")
        .child("raw")
    println("outputFile: ${outputDir.absolutePath}")
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
//checkNotNull(tasks.findByPath(":app:preBuild"))
//    .dependsOn("addOssLicenseTask")

data class Version(
    val major: Int,
    val minor: Int,
    val patch: Int,
    val rc: Int
) {
    val code = ((1 + major) * 10000 + minor * 100 + patch) * 100 + rc
    val name = "$major.$minor.$patch.$rc"
}

fun File.child(childPath: String): File {
    return File(this, childPath)
}

fun File.fileWriter(overwrite: Boolean): FileWriter {
    return FileWriter(this, overwrite)
}

fun FileWriter.newLine() {
    write(System.getProperty("line.separator"))
}