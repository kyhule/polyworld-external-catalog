plugins {
    id("version-catalog")
    alias(libs.plugins.githubRelease)
    alias(libs.plugins.mavenPublish)
    alias(libs.plugins.reckon)
}

catalog {
    versionCatalog {
        from(files("externalLibs.versions.toml"))

        fun mergeCatalog(catalog: VersionCatalog) {
            catalog.libraryAliases.forEach { alias ->
                val library = catalog.findLibrary(alias).get().get()
                library(alias, "${library.group}:${library.name}:${library.version}")
            }
        }

        mergeCatalog(versionCatalogs.find("coilLibs").get())
        mergeCatalog(versionCatalogs.find("composeLibs").get())
        mergeCatalog(versionCatalogs.find("firebaseLibs").get())
    }
}

githubRelease {
    token { System.getenv("GITHUB_TOKEN") }
    owner { "kyhule" }
    releaseName { version.toString() }
    tagName { version.toString() }
    generateReleaseNotes { true }
}

publishing {
    repositories {
        maven {
            name = "githubPackages"
            url = uri("https://maven.pkg.github.com/kyhule/polyworld-external-catalog")
            credentials(PasswordCredentials::class)
        }
    }
}

reckon {
    setDefaultInferredScope("minor")
    setScopeCalc(calcScopeFromProp())
    snapshots()
    setStageCalc(calcStageFromProp())
}
