// Top-level build file where you can add configuration options common to all sub-projects/modules.
/**
* build.gradle/build.gradle.kts(root / project)
* This defines the core plugins that are used by all your modules.
*/
plugins {
    /**
    * plugins block {}
    * This defines the core plugins that are used by all your modules.
    * In case you do not aware, the functions here are infix notation (https://vtsen.hashnode.dev/kotlin-infix-notation-is-confusing).
    * -------------------------------------------------------------------------------------------------------
    * -------------------------------------------------------------------------------------------------------
    * which is a lot more understandable in my opinion.
    *
    * id() - plugin unique id
    * version() - plugin's version
    * apply() - apply this plugin at this project level.
    *
    * Usually, you do NOT apply plugins at root project level. You apply the plugins in the module level.
    *
    * com.android.application - plugin to build Android app (e.g. default app module)
    * com.android.library - plugin to build Android library
    *
    * This can be removed if you don't build android library but no harm in keeping it here
    *
    * org.jetbrains.kotlin.android - plugin to enable Kotlin support in your project.
    */

    /**
    * Android Gradle Plugin (AGP)
    * You can specify the Gradle version in either the File > Project Structure > Project menu in Android Studio
    */
    id(ProjectPlugins.androidApp) version ProjectPlugins.androidAppVersionNumber apply false

    /**
    * plugin to enable Kotlin support in your project.
    */
    id(ProjectPlugins.kotlinAndroid) version ProjectPlugins.kotlinAndroidPluginVersionNumber apply false
}