@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("version-catalog")
    alias(libs.plugins.reckon)
}

catalog {
    versionCatalog {
        from(files("externalLibs.versions.toml"))
    }
}

reckon {
    setScopeCalc(calcScopeFromProp())
    snapshots()
    setStageCalc(calcStageFromProp())
}
