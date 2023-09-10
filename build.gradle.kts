import groovy.lang.Closure

plugins {
    base
}

tasks.build {
    dependsOn(tasks.matching { it.group.equals("build resource pack") })
}

tasks.register<Zip>("build53") {
    configure(getTexturePackConfiguration("Fancy Warp Menu Build 53"))
}

tasks.register<Zip>("build21by9") {
    configure(getTexturePackConfiguration("Fancy Warp Menu 21:9"))
}

tasks.register<Zip>("build32by9") {
    configure(getTexturePackConfiguration("Fancy Warp Menu 32:9"))
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
