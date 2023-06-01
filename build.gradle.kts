@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("version-catalog")
    alias(libs.plugins.mavenPublish)
    alias(libs.plugins.reckon)
}

catalog {
    versionCatalog {
        from(files("externalLibs.versions.toml"))
    }
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
