import groovy.lang.Closure

plugins {
    base
}

/**
 * taskName : folderName
 */
val buildPackTasks: Map<String, String> = mapOf(
    "build53" to "Fancy Warp Menu Build 53",
    "build21by9" to "Fancy Warp Menu 21:9",
    "build32by9" to "Fancy Warp Menu 32:9",
    "buildPackWithElizabeth" to "Fancy Warp Menu with Elizabeth")

tasks.build {
    dependsOn(tasks.matching { it.group.equals("build resource pack") })
}

for (taskEntry in buildPackTasks) {
    tasks.register<Zip>(taskEntry.key) {
        configure(getTexturePackConfiguration(taskEntry.value))
    }
}

/*
Copies built resource packs to the resource pack folder of a Fancy Warp Menu development instance.
This assumes the Fancy Warp Menu instance is located in "../FancyWarpMenu" relative to the root directory of this project.
 */
tasks.register<Copy>("copyToTestInstanceFolder") {
    from(layout.buildDirectory)
    into(layout.projectDirectory.dir("../FancyWarpMenu/run/resourcepacks"))
}

fun getTexturePackConfiguration(texturePackName: String): Closure<Any?> {
    return closureOf<Zip> {
        group = "build resource pack"
        archiveBaseName.set(texturePackName)
        destinationDirectory.set(layout.buildDirectory)
        from(layout.projectDirectory.dir("src/${texturePackName}"))
        filesMatching("pack.mcmeta") {
            expand("modVersion" to project.properties["fancyWarpMenuVersion"])
        }
    }
}
