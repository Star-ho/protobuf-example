import com.google.protobuf.gradle.id
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    id("com.google.protobuf")
}

group = "com.grpc"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

val grpcSpringBootStarterVersion = "5.0.0"
val protobufVersion = "3.22.0"
val grpcVersion = "1.52.1"
val grpcKotlinVersion = "1.0.0"

repositories {
    gradlePluginPortal()
    mavenCentral()
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("io.ktor:ktor-client-core:2.1.3")

    // protobuf setting
    implementation("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")
    implementation("io.github.lognet:grpc-spring-boot-starter:$grpcSpringBootStarterVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
    implementation("io.grpc:grpc-core:1.53.0")

    implementation("org.springframework.boot:spring-boot-gradle-plugin:2.7.9")
    implementation("io.spring.gradle:dependency-management-plugin")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:$protobufVersion"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:$grpcVersion"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:$grpcKotlinVersion:jdk7@jar"
        }
    }
    generateProtoTasks {
        all().forEach { generateProtoTask ->
            generateProtoTask.plugins {
                id("grpc")
                id("grpckt")
            }
        }
    }
}
