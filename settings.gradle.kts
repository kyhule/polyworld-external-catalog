import com.faendir.gradle.createWithBomSupport

rootProject.name = "polyworld-external-catalog"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("com.faendir.gradle.bom-version-catalog") version "1.5.1"
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }

    versionCatalogs {
        createWithBomSupport("coilLibs") {
            fromBom("io.coil-kt:coil-bom:2.7.0")
        }
        createWithBomSupport("composeLibs") {
            fromBom("androidx.compose:compose-bom:2024.08.00")
        }
        createWithBomSupport("firebaseLibs") {
            fromBom("com.google.firebase:firebase-bom:33.10.0")
        }
    }
}
