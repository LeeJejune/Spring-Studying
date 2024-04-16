import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.jetbrains.kotlin.plugin.allopen") version "1.9.22"
	id("org.jetbrains.kotlin.plugin.noarg") version "1.9.22"
	kotlin("jvm") version "1.9.23"
	kotlin("plugin.spring") version "1.9.23"
	kotlin("plugin.jpa") version "1.9.23"
}

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

allprojects {
	repositories {
		mavenCentral()
	}

	group = "com.message"
	version = "0.0.1-SNAPSHOT"
}
subprojects {
	apply{
		plugin("kotlin")
		plugin("org.jetbrains.kotlin.jvm")
		plugin("org.springframework.boot")
		plugin("io.spring.dependency-management")
		plugin("org.jetbrains.kotlin.plugin.allopen")
		plugin("org.jetbrains.kotlin.plugin.noarg")
		plugin("org.jetbrains.kotlin.plugin.spring")

		// KAPT(Kotlin Annotation Processing Tool)를 설치합니다 by QueryDSL
		apply(plugin = "kotlin-kapt")
	}


	allOpen {
		annotation("jakarta.persistence.Entity")
		annotation("jakarta.persistence.Embeddable")
		annotation("jakarta.persistence.MappedSuperclass")
	}

	noArg {
		annotation("jakarta.persistence.Entity")
		annotation("jakarta.persistence.Embeddable")
		annotation("jakarta.persistence.MappedSuperclass")
	}

	dependencies {
		implementation("org.axonframework:axon-spring-boot-starter:4.9.3") {
			exclude(group = "org.axonframework", module = "axon-server-connector")
		}
		implementation("org.axonframework:axon-configuration:4.9.3")
		implementation("org.axonframework.extensions.kotlin:axon-kotlin:4.6.0")

		implementation("org.springframework.boot:spring-boot-starter-data-jpa")
		implementation("org.springframework.boot:spring-boot-starter-web")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		runtimeOnly("org.postgresql:postgresql")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs += "-Xjsr305=strict"
			jvmTarget = "17"
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}