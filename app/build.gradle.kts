plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.android.ksp)
    alias(libs.plugins.navigation.safe.args)
}

android {
    namespace = "com.taufikhidayat.ceritakan"
    compileSdk = 34

    packaging {
        resources.excludes.addAll(
            listOf(
                "META-INF/*",
                "mozilla/*",
                "kotlin/*",
                "kotlin/collections/*",
                "kotlin/coroutines/*",
                "kotlin/reflect/*",
                "xsd/*",
                "META-INF/kotlin-stdlib-common.kotlin_module",
                "META-INF/kotlin-stdlib-jdk7.kotlin_module",
                "META-INF/kotlin-stdlib-jdk8.kotlin_module",
                "META-INF/kotlin-stdlib.kotlin_module",
                "kotlin/annotation/annotation.kotlin_builtins",
                "kotlin/collections/collections.kotlin_builtins",
                "kotlin/coroutines/coroutines.kotlin_builtins",
                "kotlin/internal/internal.kotlin_builtins",
                "kotlin/kotlin.kotlin_builtins",
                "kotlin/ranges/ranges.kotlin_builtins",
                "kotlin/reflect/reflect.kotlin_builtins"
            )
        )
    }

    defaultConfig {
        applicationId = "com.taufikhidayat.allnewceritaku"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // UI
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Safe args
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
//    implementation(libs.safe.args.gradle.plugin)

    // SplashScreen API
    implementation(libs.androidx.core.splashscreen)
}