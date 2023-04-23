plugins {
    id("org.quiltmc.loom") version "0.12.+"
    id("io.github.p03w.machete") version "1.+"
    id("org.cadixdev.licenser") version "0.6.+"
}

val mod_version: String by project

group = "com.example"

version = mod_version

repositories {
    val mavenUrls =
        mapOf(
            Pair("https://maven.terraformersmc.com/releases", listOf("com.terraformersmc")),
            Pair("https://api.modrinth.com/maven", listOf("maven.modrinth")),
        )

    for (mavenPair in mavenUrls) {
        maven {
            url = uri(mavenPair.key)
            content {
                for (group in mavenPair.value) {
                    includeGroup(group)
                }
            }
        }
    }
}

dependencies {
    minecraft(libs.minecraft)
    mappings(variantOf(libs.quilt.mappings) { classifier("intermediary-v2") })

    modImplementation(libs.bundles.quilt)
    modApi(libs.bundles.required)
    modImplementation(libs.bundles.optional)
    modLocalRuntime(libs.bundles.runtime)
}
