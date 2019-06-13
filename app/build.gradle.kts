plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "com.example.iaia.sampleandroidapplication"
        minSdkVersion(26)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    dataBinding {
        isEnabled = true
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Deps.kotlin_version}")
    implementation("androidx.appcompat:appcompat:1.1.0-alpha05")
    implementation("androidx.core:core-ktx:1.0.2")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.1.0-beta01")
    implementation("android.arch.lifecycle:extensions:1.1.1")

    // camerax
    implementation("androidx.camera:camera-core:${Deps.camerax_version}")
    implementation("androidx.camera:camera-camera2:${Deps.camerax_version}")

    // room
    implementation("androidx.room:room-runtime:${Deps.room_version}")
    annotationProcessor("androidx.room:room-compiler:${Deps.room_version}")
    androidTestImplementation("androidx.room:room-testing:${Deps.room_version}")

    testImplementation("junit:junit:4.12")

    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
    androidTestImplementation("androidx.test.uiautomator:uiautomator:2.2.0")

    // epoxy
    implementation("com.airbnb.android:epoxy:${Deps.epoxy_version}")
    kapt("com.airbnb.android:epoxy-processor:${Deps.epoxy_version}")
    implementation("com.airbnb.android:epoxy-databinding:${Deps.epoxy_version}")

    // koin
    implementation("org.koin:koin-androidx-scope:${Deps.koin_version}")
    implementation("org.koin:koin-androidx-viewmodel:${Deps.koin_version}")
    implementation("org.koin:koin-androidx-ext:${Deps.koin_version}")

    // permission dispatcher
    implementation("org.permissionsdispatcher:permissionsdispatcher:${Deps.permission_dispatcher_version}")
    kapt("org.permissionsdispatcher:permissionsdispatcher-processor:${Deps.permission_dispatcher_version}")


    // TODO: あとで入れるか考える
    // leakcanary, retrofit, crashlytics, espresso
}
