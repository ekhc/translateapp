import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.api.tasks.testing.Test
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.1" apply false
	id("io.spring.dependency-management") version "1.1.4" apply false
	kotlin("jvm") version "1.9.21" apply false
	kotlin("plugin.spring") version "1.9.21" apply false
}

group = "com.translate"
version = "0.0.1-SNAPSHOT"

allprojects {
	repositories {
		mavenCentral()
	}

	tasks.withType<JavaCompile> {
		sourceCompatibility = JavaVersion.VERSION_17.toString()
		targetCompatibility = JavaVersion.VERSION_17.toString()
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "17"
		}
	}
}

subprojects {
	apply {
		plugin("java-library")
		plugin("io.spring.dependency-management")
		plugin("org.jetbrains.kotlin.jvm")
		plugin("org.jetbrains.kotlin.plugin.spring")
		plugin("org.springframework.boot")
	}

	dependencies {
		"implementation"("org.springframework.boot:spring-boot-starter")
		"implementation"("org.springframework.boot:spring-boot-configuration-processor")
		"compileOnly"("org.projectlombok:lombok")
		"annotationProcessor"("org.projectlombok:lombok")
		"developmentOnly"("org.springframework.boot:spring-boot-devtools")
		"testImplementation"("org.springframework.boot:spring-boot-starter-test")
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}
