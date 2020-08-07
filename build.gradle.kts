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