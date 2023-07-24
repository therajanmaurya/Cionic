pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Cionic Android"

include(":app")
include(":core-data")
include(":core-database")
include(":core-model")
include(":core-network")
include(":core-common")
include(":core-ui")
include(":feature-cionic")
include(":test-app")
