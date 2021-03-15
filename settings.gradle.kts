pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        jcenter()
        mavenCentral()
    }
}
rootProject.name = "KMMNews"


include(":androidApp")
include(":shared")
enableFeaturePreview("GRADLE_METADATA")
