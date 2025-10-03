import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.sql.delight)
}

android {
    namespace = "com.dawn.data"

    val MIN_SDK: String by project
    val COMPILE_SDK: String by project
    compileSdk = COMPILE_SDK.toInt()

    defaultConfig {
        minSdk = MIN_SDK.toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://dev-endpoint/\"")
        }

        create("qa0") {
            buildConfigField("String", "BASE_URL", "\"https://qa0-endpoint/\"")
        }

        release {
            buildConfigField("String", "BASE_URL", "\"https://prod-endpoint/\"")

            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    sqldelight {
        databases {
            create("CodebaseDatabase") {
                packageName.set("com.dawn.database")
            }
        }
    }

    androidComponents {
        onVariants(selector().all()) { variant ->
            afterEvaluate {
                val capName = variant.name.capitalize()
                tasks.getByName<KotlinCompile>("ksp${capName}Kotlin") {
                    setSource(tasks.getByName("generate${capName}CodebaseDatabaseInterface").outputs)
                }
            }
        }
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    implementation(libs.mmkv)

    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.logging.interceptor)

    implementation(libs.sql.delight)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(project(":common"))
    implementation(project(":domain"))
}

tasks.withType<KotlinCompile>().configureEach {
    if (project.findProperty("enableReport") == "true") {
        compilerOptions.freeCompilerArgs.addAll(
            "-P",
            "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=${layout.buildDirectory.asFile.get().absolutePath}/compose_compiler",
        )
    }
}