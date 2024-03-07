import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.9" apply false
	id("io.spring.dependency-management") version "1.1.4" apply false
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22" apply false
}

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

allprojects {

	group = "com.jjlee"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}


subprojects {

	apply{
		plugin("kotlin")
		plugin("org.jetbrains.kotlin.jvm")
		plugin("org.springframework.boot")
		plugin("io.spring.dependency-management")
		plugin("org.jetbrains.kotlin.plugin.spring")

	}

	extra["springCloudVersion"] = "2022.0.5"

	dependencies {
		implementation("org.springframework.boot:spring-boot-starter-web")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
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

