import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
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