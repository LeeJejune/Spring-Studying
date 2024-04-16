plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "viewing"
include("domainA")
include("domainB")
include("application")
include("common")
