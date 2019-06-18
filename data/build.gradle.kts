plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("kotlinx-serialization")
}

android {
    compileSdkVersion(28)

    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true

            buildConfigField("String", "DUMMY_API_BASE_URL", "\"http://dummy.restapiexample.com/api/v1/\"")
            buildConfigField("String", "EXAMPLE_API_BASE_URL", "\"http://dev.example.com/\"")
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            buildConfigField("String", "DUMMY_API_BASE_URL", "\"http://dummy.restapiexample.com/api/v1/\"")
            buildConfigField("String", "EXAMPLE_API_BASE_URL", "\"http://example.com/\"")
        }
    }

}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))

    implementation("androidx.appcompat:appcompat:1.0.2")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")

    // kotlin-serialization
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Deps.kotlin_version}")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.11.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.4.0")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:${Deps.retrofit_version}")
    implementation("com.squareup.retrofit2:retrofit-mock:${Deps.retrofit_version}")
}
