package com.erill.roger.speedrun

// Idea from https://handstandsam.com/2018/02/11/kotlin-buildsrc-for-better-gradle-dependency-management/

object Version {
    // android configuration
    const val buildTools = "30.0.2"
    const val compileSdk = 29
    const val minSdk = 23
    const val targetSdk = 29
    const val versionCode = 1
    const val versionName = "1.0"

    const val androidGradlePlugin = "4.1.2"
    const val kotlin = "1.4.21"

    // Libraries
    const val coreKtx = "1.3.2"
    const val appCompat = "1.2.0"
    const val materialDesign = "1.2.1"
    const val constraintLayout = "2.0.4"
    const val junit = "4.13"
    const val androidTestJunit = "1.1.2"
    const val espresso = "3.3.0"
}

object Dependencies {

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}"
    const val junit = "junit:junit:${Version.junit}"

    object Gradle {
        const val androidGradlePlugin = "com.android.tools.build:gradle:${Version.androidGradlePlugin}"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
        const val junit = "androidx.test.ext:junit:${Version.androidTestJunit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Version.espresso}"
    }

    object Google {
        const val material = "com.google.android.material:material:${Version.materialDesign}"
    }
}