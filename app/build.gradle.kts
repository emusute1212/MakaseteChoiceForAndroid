plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.google.services)
    alias(libs.plugins.hilt)
    alias(libs.plugins.aboutlibraries)
}

android {
    val shouldMakeApk = rootProject.file("upload-keystore.jks").exists()
    val version = Version(1, 1, 0, 0)
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
            signingConfig = signingConfigs.getByName("debug")
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
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
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

    // Licenses
    implementation(libs.aboutlibraries.compose)

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
    testImplementation(libs.mockk)
    testImplementation(libs.truth)
    testImplementation(libs.coroutine.test)
    testImplementation(libs.robolectric)
    testImplementation(libs.test.core)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
}

// 自動登録されないOSSライセンスを追加する
aboutLibraries {
    configPath = "OtherLicenses"
}

data class Version(
    val major: Int,
    val minor: Int,
    val patch: Int,
    val rc: Int
) {
    val code = ((1 + major) * 10000 + minor * 100 + patch) * 100 + rc
    val name = "$major.$minor.$patch.$rc"
}
