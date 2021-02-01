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
    const val retrofit = "2.9.0"
    const val okhttp = "4.9.0"
    const val moshi = "1.11.0"
    const val rxJava = "2.2.20"
    const val rxAndroid = "2.1.1"
    const val arrow = "0.11.0"
    const val dagger = "2.29.1"
    const val mockito = "3.5.13"
    const val mockitoKotlin = "2.2.0"
    const val recyclerView = "1.1.0"
    const val glide = "4.11.0"
}

object Dependencies {

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}"
    const val junit = "junit:junit:${Version.junit}"

    object Gradle {
        const val androidGradlePlugin = "com.android.tools.build:gradle:${Version.androidGradlePlugin}"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
        const val allopen = "org.jetbrains.kotlin:kotlin-allopen:${Version.kotlin}"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
        const val junit = "androidx.test.ext:junit:${Version.androidTestJunit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Version.espresso}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Version.recyclerView}"
    }

    object Google {
        const val material = "com.google.android.material:material:${Version.materialDesign}"
    }

    object retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val rxAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Version.retrofit}"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Version.retrofit}"
    }

    object okhttp {
        const val core = "com.squareup.okhttp3:okhttp:${Version.okhttp}"
        const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"
    }

    object moshi {
        const val core = "com.squareup.moshi:moshi:${Version.moshi}"
        const val codegen = "com.squareup.moshi:moshi-kotlin-codegen:${Version.moshi}"
    }

    object rxjava2 {
        const val core = "io.reactivex.rxjava2:rxjava:${Version.rxJava}"
        const val android = "io.reactivex.rxjava2:rxandroid:${Version.rxAndroid}"
    }

    object arrow {
        const val core = "io.arrow-kt:arrow-core:${Version.arrow}"
    }

    object dagger {
        const val core = "com.google.dagger:dagger:${Version.dagger}"
        const val compiler = "com.google.dagger:dagger-compiler:${Version.dagger}"
    }

    object mockito {
        const val core = "org.mockito:mockito-core:${Version.mockito}"
        const val kotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Version.mockitoKotlin}"
        const val android = "org.mockito:mockito-android:${Version.mockito}"
    }

    object glide {
        const val core = "com.github.bumptech.glide:glide:${Version.glide}"
        const val compiler = "com.github.bumptech.glide:compiler:${Version.glide}"
    }
}