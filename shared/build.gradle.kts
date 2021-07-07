import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.4.32"
    kotlin("kapt")
    id("com.android.library")
    id("kotlin-parcelize")
}

val kotlinVersion = "1.4.32"
val coroutinesVersion = "1.4.3"
val serializationVersion = "1.1.0"
val ktorVersion = "1.5.1"

kotlin {
    android()
    ios {
        binaries {
            framework {
                baseName = "shared"
                isStatic = true
                freeCompilerArgs = listOf("-Xallocator=mimalloc")
            }
        }
    }
    sourceSets {
        all {
            languageSettings.useExperimentalAnnotation("kotlin.Experimental")
            languageSettings.enableLanguageFeature("InlineClasses")
        }

        val commonMain by getting {
            dependencies {
                // implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.1.1")
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3-native-mt")

                // Ktor
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3-native-mt")

                // Ktor
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")

                // Square
                implementation("com.squareup.okio:okio:2.10.0")
                implementation("com.squareup.okhttp3:okhttp:4.9.1")
                implementation("com.tencent:mmkv-static:1.2.10")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13")
            }
        }
        val iosMain by getting {
            dependencies {
                // Ktor
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
            }
        }
        val iosTest by getting
    }
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")
    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    sourceSets {
        getByName("main") {
            manifest.srcFile(File("src/androidMain/AndroidManifest.xml"))
            java.setSrcDirs(listOf("src/androidMain/kotlin"))
            res.setSrcDirs(listOf("src/androidMain/res"))
            assets.setSrcDirs(listOf("src/androidMain/assets"))
        }
    }
}

val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
    val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
    val framework = kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}

tasks.getByName("build").dependsOn(packForXcode)