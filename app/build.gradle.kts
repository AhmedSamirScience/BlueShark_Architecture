plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    /**
     * android {} Block -> This defines the properties specific to the Android platform.
     */


    /**
     * namespace -> namespace for this project, where all the generated code is based on this namespace.
     */
    namespace = ProjectConfiguration.appId

    /**
     * compileSdk -> API level is used by Gradle to compile your app(usually the same as targetSdk)
     *
     * (More Details about compileSdk)
     *
     * If your app uses an API that is introduced in API level 26, your compileSdk must be set to minimum 26. If you set it to 25, it fails to compile.
     *
     * Assuming the minSdk is 21, you likely get this warning/error too
     *
     * Call requires API level 26 (current min is 21): android.app.NotificationChannel()
     *
     * As recommended by the Android Studio IDE, you can fix the warning/error by annotating your function with
     *
     * @RequiresApi(Build.VERSION_CODES.O)
     * private fun yourFunction() {
     *     /*...*/
     * }
     *
     * However, if you run your App on the Android version (< API level 26), it will NOT crash but fails silently. You will notice the error messages in the logcat. So, I think adding the @RequiresApi() is a bad practice here.
     *
     * What you should do is handle the code above the app runs below the API level 26.
     *
     * if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
     *     /* Code for API level >= 26 */
     * } else {
     *    /* code for API level >= 23 and < 26) */
     * }
     *
     *
     * An example is the notification channel is only required / available for API level 26. Then, we should wrap it with Build.VERSION_CODES.O.
     *
     *
     *if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
     *
     *     val notificationChannel = NotificationChannel(
     *         notificationChannelId,
     *         "DemoWorker",
     *         NotificationManager.IMPORTANCE_DEFAULT,
     *     )
     *
     *     val notificationManager: NotificationManager? =
     *         getSystemService(applicationContext, NotificationManager::class.java)
     *
     *     notificationManager?.createNotificationChannel(notificationChannel)
     * }
     *
     */
    compileSdk = ProjectConfiguration.compileSdk

    defaultConfig {
        /**
         * defaultConfig {} Block -> This specifies the default configuration for the project.
         */


        /**
         * applicationId -> Unique ID that is used to identify your app on a device or in Google Play Store.
         */
        applicationId = ProjectConfiguration.appId

        /**
         * minSdk -> Minimum API Level required for the app to run
         *
         * (More Details about minSdk)
         * If your minSdk is set to 21, your app cannot be run on any Android version that is below API level 21. If the Android version (API level 20) attempts to install your app, you get this error.
         *
         * Installation did not succeed. The application could not be installed: INSTALL_FAILED_OLDER_SDK
         *
         * The Google Play Store prevents the user from installing the app too if the phone's Android version doesn't meet the minSdk requirement by the app.
         */
        minSdk = ProjectConfiguration.minSdk

        /**
         * targetSdk -> API level the app is designed and tested on (usually the same as compileSdk)
         *
         * (More Details about targetSdk)
         * (**App runs on API level > targetSdk**)
         * If the app is run on the Android version (API level) that is higher than the targetSdk, the Android operating system will try to run backward compatibility behavior to match behavior as in targetSdk API level.
         *
         * For example, runtime app permission is introduced in API level 23. Before API level 23, runtime app permission is not needed.
         *
         * If your targetSdk is set to 22 and your app runs on Android version (API level 23), the Android OS will try to match the behavior as in API level 22. Thus, no runtime permission is requested.
         *
         * If your targetSdk is set to 23 and your app runs on Android version (API level 23 or higher), runtime permission is requested.
         *
         * (**App runs on API level < targetSdk**)
         * What about the app that runs on the Android version (API level) that is < targetSdk? The app behaves based on that Android version (API level).
         *
         * If your targetSdk is set to 23 and your app runs on Android version (API level 22), runtime permission is not requested.
         */
        targetSdk = ProjectConfiguration.targetSdk


        /**
         * versionCode - an integer value that represents the version of your app
         */
        versionCode = ProjectConfiguration.versionCode


        /**
         * versionName - string value that represents the user-visible version of your app.
         * It can be any string but is usually based on <major>.<minor>.<point> version format.
         * It doesn't need to match the version code.
         */
        versionName = ProjectConfiguration.versionName

        /**
         * testInstrumentationRunner - specify the library to run the instrumented test
         */
        testInstrumentationRunner = ProjectConfiguration.testInstrumentationRunner

        /**
         * vectorDrawable - set "useSupportLibrary = true" to enable the vector drawable support for your app
         */
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}