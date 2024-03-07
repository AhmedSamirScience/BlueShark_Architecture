package dependencies

import org.gradle.api.artifacts.dsl.DependencyHandler
    object Dependencies {

        //region default
        const val androidCore = "androidx.core:core-ktx:${Versions.androidCore}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val androidMaterial = "com.google.android.material:material:${Versions.androidMaterial}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val junit = "junit:junit:${Versions.junit}"
        const val testExt = "androidx.test.ext:junit:${Versions.testExt}"
        const val testEspresso = "androidx.test.espresso:espresso-core:${Versions.testEspresso}"
        //endregion

    }

    fun DependencyHandler.defaultLibraries() {
        implementation(Dependencies.androidCore)
        implementation(Dependencies.appCompat)
        implementation(Dependencies.androidMaterial)
        implementation(Dependencies.constraintLayout)
        testImplementation(Dependencies.junit)
        androidTestImplementation(Dependencies.testExt)
        androidTestImplementation(Dependencies.testEspresso)
    }


