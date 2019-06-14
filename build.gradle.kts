buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.4.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Deps.kotlin_version}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${Deps.kotlin_version}")
        classpath("com.google.android.gms:oss-licenses-plugin:0.9.5")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        
    }
}

tasks.create("clean", type = Delete::class) {
    delete(rootProject.buildDir)
}
