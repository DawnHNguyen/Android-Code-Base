import org.gradle.api.JavaVersion

object SdkVersions {
    const val compileSdkVersion = 32
    const val buildToolsVersion = "31.0.0"
    const val minSdkVersion = 23
    const val targetSdkVersion = 32

    val javaVersion = JavaVersion.VERSION_1_8
    const val jvmTarget = "1.8"
}

object Versions {
    const val kotlin = "1.5.31"
    const val room = "2.4.3"
    const val timber = "4.7.1"
    const val hyperion = "0.9.34"
    const val viewPager2 = "1.0.0"
    const val activity = "1.5.1"
    const val fragment = "1.5.2"
    const val playService = "21.0.1"

    const val appCompat = "1.5.0"
    const val coreKtx = "1.5.0-alpha01"
    const val constraintLayout = "2.1.4"
    const val material = "1.6.1"
    const val annotation = "1.4.0"
    const val legacy = "1.0.0"

    const val recyclerview = "1.2.0-alpha05"

    const val hilt = "2.38.1"
    const val hiltNav = "1.0.0"

    const val lottie = "5.2.0"

    const val nav = "2.5.2"

    const val core_version = "1.7.0"

    const val coroutines = "1.3.8"

    const val lifecycle = "2.3.0-alpha06"

    const val firebase_version = "4.3.13"
    const val crashlytic_version = "2.8.1"

    const val swipe_reveal = "1.4.1"

    const val lifecycle_livedata = "2.5.0-alpha02"

    const val gson = "2.8.6"
    const val retrofit = "2.9.0"
    const val loggingIntercepter = "4.10.0"

    const val hawk = "2.0.1"

    const val glide = "4.13.2"

    const val junit = "4.+"
    const val ext = "1.1.3"
    const val espresso = "3.4.0"
}

object Libraries {
    // ROOM
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    // TIMBER
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    //HILT
    const val daggerHilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-fragment:${Versions.hiltNav}"

    // NOTIFICATION
    const val notification = "androidx.core:core-ktx:${Versions.core_version}"

    //FIREBASE
    const val firebase = "com.google.gms:google-services:${Versions.firebase_version}"

    // CRASHLYTIC
    const val crashlytic = "com.google.firebase:firebase-crashlytics-gradle:${Versions.crashlytic_version}"

    /// Hyperion
    const val hyperionCore = "com.willowtreeapps.hyperion:hyperion-core:${Versions.hyperion}"
    const val hyperionCrash = "com.willowtreeapps.hyperion:hyperion-crash:${Versions.hyperion}"
    const val hyperionMeasurement = "com.willowtreeapps.hyperion:hyperion-measurement:${Versions.hyperion}"

    // LOTTIE
    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
}

object KotlinLibraries {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
}

object AndroidLibraries {
    //Coroutine
    const val kotlinCoroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    //Play Service
    const val gmsPlayService = "com.google.android.gms:play-services-location:${Versions.playService}"

    // ANDROID
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val annotation = "androidx.annotation:annotation:${Versions.annotation}"
    const val legacy = "androidx.legacy:legacy-support-v4:${Versions.legacy}"
    const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
    const val activity = "androidx.activity:activity-ktx:${Versions.activity}"
    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"

    // NAVIGATION
    const val navigation = "androidx.navigation:navigation-ui-ktx:${Versions.nav}"
    const val navigationFrag = "androidx.navigation:navigation-fragment-ktx:${Versions.nav}"

    // SAFE ARGS
    const val safeArgs = "androidx.navigation.safeargs.kotlin:androidx.navigation.safeargs.kotlin.gradle.plugin:${Versions.nav}"

    // SWIPE REVEAL RECYCLERVIEW
    const val swipeReviewRecyclerview = "com.chauthai.swipereveallayout:swipe-reveal-layout:${Versions.swipe_reveal}"

    //LIFECYCLE LIVEDATA
    const val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_livedata}"

    //RETROFIT
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    //OKHTTP3
    const val loggingIntercepter = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingIntercepter}"

    //Hawk
    const val hawk = "com.orhanobut:hawk:${Versions.hawk}"

    //GLIDE
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"

}

object Modules {
    const val navigation = ":navigation"

    const val common = ":common"

}

object TestLibraries{
    const val junit = "junit:junit:${Versions.junit}"
    const val ext = "androidx.test.ext:junit:${Versions.ext}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

}

object Kapt {

}