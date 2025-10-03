import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.gms.google.services)
    alias(libs.plugins.firebase.crashlytics)
}

android {
    namespace = "com.dawn.codebase"
    val MIN_SDK: String by project
    val COMPILE_SDK: String by project
    val TARGET_SDK: String by project
    compileSdk = COMPILE_SDK.toInt()

    val KEYSTORE_FILE: String by project
    val KEYSTORE_ALIAS: String by project
    val KEYSTORE_PASSWORD: String by project

    defaultConfig {
        val versionCodeProperty = project.findProperty("versionCode")?.toString()?.toIntOrNull() ?: 1

        applicationId = "com.dawn.codebase"
        minSdk = MIN_SDK.toInt()
        targetSdk = TARGET_SDK.toInt()
        versionCode = versionCodeProperty
        versionName = System.getenv("VERSION_NAME") ?: "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        getByName("debug") {
            storeFile = file("debug.keystore")
            storePassword = "android"
            keyAlias = "debugkey"
            keyPassword = "android"
        }
//        create("release") {
//            storeFile = file(KEYSTORE_FILE)
//            storePassword = KEYSTORE_PASSWORD
//            keyAlias = KEYSTORE_ALIAS
//            keyPassword = KEYSTORE_PASSWORD
//        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false

            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        create("qa0") {
            initWith(getByName("release"))
            isDebuggable = true
            matchingFallbacks.add("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.process)

    implementation(libs.mmkv)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.config)

    implementation(libs.splash.screen)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    implementation(project(":feature:core"))
    implementation(project(":common"))
    implementation(project(":navigation"))
    implementation(project(":domain"))
    implementation(project(":data"))
}

tasks.withType<KotlinCompile>().configureEach {
    if (project.findProperty("enableReport") == "true") {
        compilerOptions.freeCompilerArgs.addAll(
            "-P",
            "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=${layout.buildDirectory.asFile.get().absolutePath}/compose_compiler",
        )
    }
}