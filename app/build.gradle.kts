plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
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

    // Dagger2
    implementation(Dependencies.ThirdParty.Dagger.android)
    implementation(Dependencies.ThirdParty.Dagger.androidSupport)
    kapt(Dependencies.ThirdParty.Dagger.androidProcessor)
    kapt(Dependencies.ThirdParty.Dagger.compiler)

    // Groupie
    implementation(Dependencies.ThirdParty.Groupie.core)
    implementation(Dependencies.ThirdParty.Groupie.kotlinAndroidExtension)
    implementation(Dependencies.ThirdParty.Groupie.databinding)

    // Test
    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.AndroidX.Test.runner)
    androidTestImplementation(Dependencies.AndroidX.Test.espresso)
}
