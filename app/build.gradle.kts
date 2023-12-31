plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.kader.shoppinglisttesting"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kader.shoppinglisttesting"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "API_KEY", (project.findProperty("API_KEY") ?: "\"\"") as String)
        testInstrumentationRunner = "com.kader.shoppinglisttesting.HiltTestRunner"
    }

    buildFeatures {
        buildConfig = true
    }

    viewBinding {
        enable = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Architectural Components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    // Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:2.6.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Coroutine Lifecycle Scopes
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // Navigation Components
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.13.2")
    kapt("com.github.bumptech.glide:compiler:4.12.0")

    // Activity KTX for viewModels()
    implementation("androidx.activity:activity-ktx:1.8.1")

    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.49")
    kapt("com.google.dagger:hilt-compiler:2.49")

    kapt("androidx.hilt:hilt-compiler:1.1.0")

    // Timber
    implementation("com.jakewharton.timber:timber:4.7.1")

    // Local Unit Tests
    implementation("androidx.test:core:1.5.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.hamcrest:hamcrest-all:1.3")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("org.robolectric:robolectric:4.3.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.2.1")
    testImplementation("com.google.truth:truth:1.1")
    testImplementation("org.mockito:mockito-core:2.21.0")
    testImplementation("com.google.dagger:hilt-android-testing:2.49")
    kaptTest("com.google.dagger:hilt-compiler:2.49")


    // Instrumented Unit Tests
    androidTestImplementation("junit:junit:4.13.2")
    //androidTestImplementation("com.linkedin.dexmaker:dexmaker-mockito:2.12.1")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.2.1")
    androidTestImplementation("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation("com.google.truth:truth:1.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //androidTestImplementation("org.mockito:mockito-core:2.25.0")
    androidTestImplementation("org.mockito:mockito-android:4.4.0")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.49")
    kaptAndroidTest("com.google.dagger:hilt-compiler:2.49")
    debugImplementation("androidx.fragment:fragment-testing:1.3.0-alpha08")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.3.0")

}