plugins {
    base
}

tasks.build {
    dependsOn("build53", "buildUltraWide")
}

tasks.register<Zip>("build53") {
    archiveBaseName.set("Fancy Warp Menu Build 53")
    destinationDirectory.set(layout.buildDirectory)
    from(layout.projectDirectory.dir("src/Fancy Warp Menu Build 53"))
    filesMatching("pack.mcmeta") {
        expand("modVersion" to project.properties["fancyWarpMenuVersion"])
    }
}

tasks.register<Zip>("buildUltraWide") {
    archiveBaseName.set("Fancy Warp Menu UltraWide")
    destinationDirectory.set(layout.buildDirectory)
    from(layout.projectDirectory.dir("src/Fancy Warp Menu UltraWide"))
    filesMatching("pack.mcmeta") {
        expand("modVersion" to project.properties["fancyWarpMenuVersion"])
    }
}
