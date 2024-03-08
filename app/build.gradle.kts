import dependencies.defaultLibraries
/**
 * ***build.gradle/build.gradle.kts (app module)***
 *
 * All these blocks (e.g. plugins {}, android {}, dependencies {}, etc) are domain-specify language (DSL)
 * implemented using trailing lambda -> (https://vtsen.hashnode.dev/what-is-trailing-lambda-and-comma-in-kotlin) and
 * function literal with receiver -> (https://vtsen.hashnode.dev/understand-kotlin-function-literal-with-receiver-by-example)
 */

plugins {
    /**
     * plugins {} Block
     * Similar to the build.gradle/.kts in the root project, this applies plugins in this module.
     * It doesn't need to explicitly call version() because it has already been specified in the root project.
     * apply(true) is also not needed because by default it is true.
     */

    id(ProjectPlugins.androidApp) //plugin to build Android app (e.g. default app module) (AGP)
    id(ProjectPlugins.kotlinAndroid) //plugin to enable Kotlin support in your project.
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
     *-----------------------------------------------------------------
     * (More Details about compileSdk)
     *-----------------------------------------------------------------
     * If your app uses an API that is introduced in API level 26, your compileSdk must be set to minimum 26. If you set it to 25, it fails to compile.
     * Assuming the minSdk is 21, you likely get this warning/error too
     *
     * -----> Call requires API level 26 (current min is 21): android.app.NotificationChannel() <-----
     *
     * As recommended by the Android Studio IDE, you can fix the warning/error by annotating your function with
     *-----------------------------------------------------------------
     * @RequiresApi(Build.VERSION_CODES.O)
     * private fun yourFunction() {
     *     /*...*/
     * }
     *-----------------------------------------------------------------
     *
     * However, if you run your App on the Android version (< API level 26), it will NOT crash but fails silently. You will notice the error messages in the logcat. So, I think adding the @RequiresApi() is a bad practice here.
     * What you should do is handle the code above the app runs below the API level 26.
     *
     *-----------------------------------------------------------------
     * if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
     *     /* Code for API level >= 26 */
     * } else {
     *    /* code for API level >= 23 and < 26) */
     * }
     *-----------------------------------------------------------------
     *
     * An example is the notification channel is only required / available for API level 26. Then, we should wrap it with Build.VERSION_CODES.O.
     *
     *-----------------------------------------------------------------
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
     *-----------------------------------------------------------------
     * Conclusion
     *-----------------------------------------------------------------
     * In general,
     * minSdk < targetSdk <= compileSdk
     *
     * but ideally and practically (100% good work),
     * minSdk < targetSdk == compileSdk == latest SDK version
     *
     * Don't use @RequiresApi() and handle the different API versions' behavior in the code.
     *
     * Here is the reference -> https://vtsen.hashnode.dev/minsdk-vs-targetsdk-vs-compilesdk
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
         *-----------------------------------------------------------------
         * (More Details about minSdk)
         *-----------------------------------------------------------------
         * If your minSdk is set to 21, your app cannot be run on any Android version that is below API level 21. If the Android version (API level 20) attempts to install your app, you get this error.
         *
         * Installation did not succeed. The application could not be installed: INSTALL_FAILED_OLDER_SDK
         *
         * The Google Play Store prevents the user from installing the app too if the phone's Android version doesn't meet the minSdk requirement by the app.
         *
         * Here is the reference -> https://vtsen.hashnode.dev/minsdk-vs-targetsdk-vs-compilesdk
         */
        minSdk = ProjectConfiguration.minSdk

        /**
         * targetSdk -> API level the app is designed and tested on (usually the same as compileSdk)
         *-----------------------------------------------------------------
         * (More Details about targetSdk)
         * -----------------------------------------------------------------
         * (**App runs on API level > targetSdk**)
         * If the app is run on the Android version (API level) that is higher than the targetSdk, the Android operating system will try to run backward compatibility behavior to match behavior as in targetSdk API level.
         *
         * For example, runtime app permission is introduced in API level 23. Before API level 23, runtime app permission is not needed.
         *
         * If your targetSdk is set to 22 and your app runs on Android version (API level 23), the Android OS will try to match the behavior as in API level 22. Thus, no runtime permission is requested.
         *
         * If your targetSdk is set to 23 and your app runs on Android version (API level 23 or higher), runtime permission is requested.
         *-----------------------------------------------------------------
         * (**App runs on API level < targetSdk**)
         * What about the app that runs on the Android version (API level) that is < targetSdk? The app behaves based on that Android version (API level).
         *
         * If your targetSdk is set to 23 and your app runs on Android version (API level 22), runtime permission is not requested.
         *
         * ------------------------------------------------------------------------------------------------------------------------------------------------------------
         *                runtime app permission is introduced in API level 23 or higher ||| Before API level 23, runtime app permission is not needed.
         * ------------------------------------------------------------------------------------------------------------------------------------------------------------
         *                                                          In case Of (App runs on API level > targetSdk)
         *                                                          ----------------------------------------------
         * target SDK  22    ||||   your app runs on Android version (API level 23)             ----> no runtime permission is requested. (the android OS will try to match the behavior as in the Api level 22)
         *
         * target SDK  23    ||||   your app runs on Android version (API level 23 or higher)   ----> runtime permission is requested. (the android OS will try to match the behavior as in the Api level 22)
         * ------------------------------------------------------------------------------------------------------------------------------------------------------------
         *                                                          In case Of (App runs on API level < targetSdk)
         *                                                          ----------------------------------------------
         * target SDK  23    ||||   your app runs on Android version (API level 22)             ----> runtime permission is not requested.
         *
         * Here is the reference -> https://vtsen.hashnode.dev/minsdk-vs-targetsdk-vs-compilesdk
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
        /**
        * compileOptions {} Block -> This specifies options related to compiling your Java code.
        */

        /**
        *  specifies the Java version that your code uses.
        */
        sourceCompatibility = JavaVersion.VERSION_17

        /**
        * specifies the Java runtime version that your code will be executed on.
        */
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        /**
        * kotlinOptions {} Block
        */

        /**
        * This specifies options related to compiling your Kotlin code.
        *
        * jvmTarget - specifies the Java Virtual Machine (JVM) version your code will be compiled for. In short, it compiles your code to byte code that is compatible with the JVM version that you specified.
        * If you don't specify any compile / Kotlin options here, the default value will be used which could be different for a different version of the build Gradle plugin.
        *
        * Technically, JVM is for desktop app. For Android app, the runtime is called Dalvik Virtual Machine (DVM) which has been replaced by Android Runtime (ART).
        *
        * If you are curious about the Kotlin compilation process for Android app, you can read this article.
        * https://vtsen.hashnode.dev/android-vs-desktop-app-kotlin-compilation-process
        *
        *
        * Here is another reference : https://vtsen.hashnode.dev/android-vs-desktop-app-kotlin-compilation-process
        */
        jvmTarget = "17"
    }
    packaging {
        /**
        * packagingOptions {} Block
        * This specifies certain resources to exclude from the Android package - APK or Android bundle files.
        */

        resources {
            /**
            * resources - specifies the set of resources that should be processed during the packaging process
            *
            * In the above example, it excludes the following license files:
            * META-INF/AL2.0
            * META-INF/LGPL2.1
            *
            * The purpose is to reduce the Android package file size.
            */
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    /**
     * dependencies {} Block
     *
     * This declares the dependencies/libraries that your code is needed.
     * implementation - specifies the library version that should be included in the Android package (APK/ Android bundle).
     *
     * Note: If you have unused dependencies in your Android project, it is better to remove them. This can help reduce the size of the Android package (APK/ Android bundle) and improve build times.
     */

    defaultLibraries()
}