// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
        maven { url = java.net.URI.create("https://dl.bintray.com/lisawray/maven") }
    }
    dependencies {
        classpath(Dependencies.Gradle.plugin)
        classpath(Dependencies.Gradle.kotlinPlugin)
        classpath(Dependencies.Google.ossLicensesPlugin)

        // Firebase
        classpath(Dependencies.Google.googleService)
        // Crashlytics
        classpath(Dependencies.Google.Firebase.crashlyticsGradle)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = java.net.URI.create("https://dl.bintray.com/lisawray/maven") }
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}