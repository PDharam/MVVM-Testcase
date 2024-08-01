plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.pdharam.mvvmtest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.pdharam.mvvmtest"
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

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.glide)
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.okhttp.mockserver)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //core-testing
    testImplementation(libs.androidx.junit.core.testing)
    androidTestImplementation(libs.androidx.junit.core.testing)
    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.convertergson)
    //Okhttp
    implementation(libs.okhttp)
    implementation(libs.okhttp.logginginterceptor)
    //Coroutine
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)
    //view Model
    implementation(libs.lifecycle.viewmodelktx)
    implementation(libs.lifecycle.livedataktx)
    implementation(libs.lifecycle.runtimektx)
}