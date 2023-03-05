import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    id("org.springframework.boot") version "2.7.9"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    // protobuf setting
    id("com.google.protobuf") version "0.9.2"

}

//protobuf setting
val protobufVersion = "3.19.1"
val grpcVersion = "1.42.1"

group = "com.grpc"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // protobuf setting
    implementation("com.google.protobuf:protobuf-java:3.22.0")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("net.devh:grpc-spring-boot-starter:2.14.0.RELEASE")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

//protobuf setting

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
