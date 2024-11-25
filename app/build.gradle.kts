plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("org.sonarqube") version "4.3.0.3225"
    id("jacoco")

    //Doumentacion Tecnica
    id("org.jetbrains.dokka") version "1.9.0"
}

android {
    namespace = "com.example.geniuskids"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.geniuskids"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        viewBinding = true
    }
}

jacoco {
    toolVersion = "0.8.7"
}

tasks.withType<Test> {
    extensions.configure(JacocoTaskExtension::class) {
        isIncludeNoLocationClasses = true
        excludes = listOf("jdk.internal.*")
    }
}

tasks.register<JacocoReport>("jacocoTestReport") {
    dependsOn(tasks.named("testDebugUnitTest"))

    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    classDirectories.setFrom(
        fileTree(
            mapOf(
                "dir" to "${project.buildDir}/intermediates/javac/debug",
                "excludes" to listOf(
                    "**/R.class",
                    "**/R$*.class",
                    "**/BuildConfig.*",
                    "**/Manifest*.*",
                    "**/*Test*.*"
                )
            )
        )
    )

    sourceDirectories.setFrom(files("src/main/java", "src/main/kotlin"))
    executionData.setFrom(fileTree(mapOf("dir" to project.buildDir, "includes" to listOf("**/jacoco/*.exec"))))
}

dependencies {

    //-----------------Base de Firebase---------------------------------
    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.firebaseui:firebase-ui-auth:7.2.0")
    implementation("com.google.android.gms:play-services-auth:21.0.0")
    implementation("com.google.firebase:firebase-auth:21.0.1")
    implementation ("com.google.firebase:firebase-firestore:24.6.0")
    //-------------
    implementation("androidx.webkit:webkit:1.8.0")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")
    implementation("androidx.activity:activity:1.8.0")
    implementation("com.google.firebase:firebase-database-ktx:21.0.0")
    implementation("androidx.gridlayout:gridlayout:1.0.0")
    implementation("com.google.firebase:firebase-storage-ktx:21.0.1")

    //----------------------------PLAN DE PRUEBAS--------------------------------------------------
    testImplementation ("io.mockk:mockk:1.13.5")
    androidTestImplementation ("io.mockk:mockk-android:1.13.5")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //---------------------------------------------------------------------------------------------

    implementation ("androidx.drawerlayout:drawerlayout:1.1.1")
    implementation ("com.google.android.material:material:1.9.0")

    //Mongo
    implementation ("io.realm:realm-android-library:10.10.0")
    implementation ("org.mongodb:mongodb-driver-sync:4.3.0")

    //---------------------------------------------------------------------------------
    implementation("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:2.7.1")
    //---------------------------------------------------------------------------------

    //----------------------------Perfil de Google--------------------------------------------------
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1")

    //-----------------------------------Animacion con JSON-----------------------------------------
    implementation ("com.airbnb.android:lottie:6.0.1")

}
