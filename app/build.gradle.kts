plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
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
    }

}

dependencies {
    // Kotlin
    implementation(Dependencies.Kotlin.stdLib)

    // AppCompat
    implementation(Dependencies.AndroidX.appCompat)

    // Ktx
    implementation(Dependencies.AndroidX.ktx)

    // Ui
    implementation(Dependencies.AndroidX.constraintLayout)

    // Navigation
    implementation(Dependencies.JetPack.Navigation.core)
    implementation(Dependencies.JetPack.Navigation.ktx)

    // Test
    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.AndroidX.Test.runner)
    androidTestImplementation(Dependencies.AndroidX.Test.espresso)
}
