buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.4.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Deps.kotlin_version}")
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
