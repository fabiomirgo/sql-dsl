plugins {
    kotlin("jvm") version "1.3.72"
}

group = "com.sixty4bits"
version = "1.0.0"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.4.2")
}

tasks {
    test {
        useJUnitPlatform()
    }
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
