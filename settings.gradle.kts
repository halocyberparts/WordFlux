pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "WordFlux_v11"
include(":app")
