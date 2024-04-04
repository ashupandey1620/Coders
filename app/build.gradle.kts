plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.ashutosh.bingo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ashutosh.bingo"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt") ,
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    ksp {
        arg(
            "room.schemaLocation",
            "$projectDir/schemas"
        )
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")



    //room
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

    //hilt
    implementation("com.google.dagger:hilt-android:2.49")
    kapt("com.google.dagger:hilt-android-compiler:2.49")

    //navigation
    implementation("androidx.navigation:navigation-compose:2.7.6")

    //lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")

    //time picker
    implementation("com.github.commandiron:WheelPickerCompose:1.1.11")

    //material icons extended
    implementation("androidx.compose.material:material-icons-extended:1.5.4")

    //acra - crash reports
    implementation("ch.acra:acra-mail:5.11.3")
    implementation("ch.acra:acra-dialog:5.11.3")

    //work manager
    implementation("androidx.work:work-runtime-ktx:2.9.0")

    //data store
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    //splash screen
    implementation("androidx.core:core-splashscreen:1.0.1")

    //gson
    implementation("com.google.code.gson:gson:2.10.1")

    //calender
    implementation("com.kizitonwose.calendar:compose:2.4.1")


    //Mobile UI Controller Background
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.20.0")

    //Bottom sheet dialog compose
    implementation ("com.holix.android:bottomsheetdialog-compose:1.4.0")

    //Retrofit and LifeCycle Livedata Support for the ViewModels
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.google.code.gson:gson:2.9.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel:2.3.1")
    implementation ("androidx.lifecycle:lifecycle-livedata:2.3.1")

    //okhttp Network logging interceptor
    implementation ("com.squareup.okhttp3:logging-interceptor:4.12.0")

    //Navigation
    implementation ("androidx.navigation:navigation-compose:2.6.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
}