import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")

	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.3")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.3")

	// serializer
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
}
dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}


tasks.getByName<BootJar>("bootJar") {
	enabled = true
}

tasks.getByName<Jar>("jar") {
	enabled = false
}