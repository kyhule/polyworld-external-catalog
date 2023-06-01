@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("version-catalog")
}

catalog {
    versionCatalog {
        from(files("externalLibs.versions.toml"))
    }
}
