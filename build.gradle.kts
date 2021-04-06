buildscript {
    val kotlin_version by extra("1.4.32")
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("com.android.tools.build:gradle:4.1.3")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}