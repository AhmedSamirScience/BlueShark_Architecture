package dependencies

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependency: String) {
    /**
     * the meaning of implementation is that i will use this library to the whole of application i mean
     * androidTest, main, and test{unitTest}
     */
    add("implementation", dependency)
}
fun DependencyHandler.test(dependency: String) {
    add("test", dependency)
}
fun DependencyHandler.androidTest(dependency: String) {
    add("androidTest", dependency)
}
fun DependencyHandler.debugImplementation(dependency: String) {
    add("debugImplementation", dependency)
}
fun DependencyHandler.kapt(dependency: String) {
    add("kapt", dependency)
}
fun DependencyHandler.testImplementation(dependency: String) {
    /**
     * the meaning of testImplementation is that i will use this library inside only package name (test) inside app module
     * i mean i will use it inside this package -> test{unitTest}
     */
    add("testImplementation", dependency)
}
fun DependencyHandler.androidTestImplementation(dependency: String) {
    /**
     * the meaning of androidTestImplementation is that i will use this library inside only package name (android-test) inside app module
     * i mean i will use it inside this package -> androidTest
     */
    add("androidTestImplementation", dependency)
}

/**
 *
    fun DependencyHandler.debugImplementation (dependency: String) {
        /**
         * the meaning of debugImplementation is that i will use this library for debug only
         */
        add("debugImplementation ", dependency)
    }
    fun DependencyHandler.releaseImplementation(dependency: String) {
        /**
        * the meaning of releaseImplementation is that i will use this library for release only
         */
        add("releaseImplementation", dependency)
    }
    fun DependencyHandler.api(dependency: String) {
        /**
        * If you want to get more details about it then you can watch azmi playlist that i have mentioned
        * also there is also another playlist inside azmi channel called modularization it will be helpful
        * to understand it very will
        */
        add("api", dependency)
    }
 */