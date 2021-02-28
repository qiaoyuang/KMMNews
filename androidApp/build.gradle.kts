plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
}

val lifecycle_version = "2.2.0"

dependencies {
    implementation(project(":shared"))

    // AndroidX
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")

    // KTX
    implementation("androidx.core:core-ktx:1.3.2")

    // Arch
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version")

    // 图片加载
    implementation("io.coil-kt:coil:1.1.1")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")
    sourceSets["main"].java.srcDirs("src/main/kotlin")
    defaultConfig {
        applicationId = "com.qiaoyuang.kmmnews.androidApp"
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}