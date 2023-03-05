import com.google.protobuf.gradle.GenerateProtoTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.9"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    id("com.google.protobuf") version "0.9.2"
}

group = "com.grpc"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

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
    implementation("com.google.protobuf:protobuf-java:3.22.0")

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
val grpcVersion = "1.53.0"
val protobufVersion = "3.22.0"
val generatedFilesBaseDir = "$projectDir/src/generated"


//protobuf {
//    protoc{
//        artifact = "com.google.protobuf:protobuf-java:3.22.0"
//    }
//
//    generateProtoTasks{
//        all().forEach {task ->
//                task.builtins{
//                    remove(GenerateProtoTask.PluginOptions("java"))
//                }
////            task.plugins {
////                javalite {}
////            }
//
//        }
//    }
//}