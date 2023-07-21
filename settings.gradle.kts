pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "CodingChallengeWeather"
include(":app")

include(":core:common")
include(":core:data")
include(":core:datastore")
include(":core:location")
include(":core:network")
include(":core:model")
include(":core:testing")
include(":core:ui")

include(":features:weather")
include(":features:search")
