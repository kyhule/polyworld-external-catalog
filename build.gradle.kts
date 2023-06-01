@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("version-catalog")
    alias(libs.plugins.githubRelease)
    alias(libs.plugins.mavenPublish)
    alias(libs.plugins.reckon)
}

catalog {
    versionCatalog {
        from(files("externalLibs.versions.toml"))
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
    setScopeCalc(calcScopeFromProp())
    snapshots()
    setStageCalc(calcStageFromProp())
}
