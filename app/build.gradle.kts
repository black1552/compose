@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.android.library")
    alias(libs.plugins.kotlinAndroid)
    id("maven-publish")
}

afterEvaluate {
    publishing { // 发布配置
        publications { // 发布的内容
            create<MavenPublication>("release") { // 注册一个名字为 release 的发布内容
                groupId = "com.github.black1552"
                artifactId = "compose"
                version = "1.0"

                afterEvaluate { // 在所有的配置都完成之后执行
                    // 从当前 module 的 release 包中发布
                    from(components["release"])
                }
            }
        }
    }
}


android {
    namespace = "com.black1552.compose"
    compileSdk = 33

    defaultConfig {
        minSdk = 29

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    implementation(libs.xutil.core)
    implementation(libs.xutil.sub)
    implementation(libs.xaop.runtime)
    implementation(libs.x.update)
    implementation(libs.x.http2)
    implementation(libs.gson)
    implementation(libs.okhttp)
    api(libs.retrofit)
    api(libs.converterScalars)
    api(libs.converterGson)
    api(libs.adapterRxjava2)
    api(libs.rxjava)
    api(libs.rxandroid)
    api(libs.utilcodex)
    api(libs.coil.compose)
}