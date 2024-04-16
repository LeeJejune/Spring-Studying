import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    implementation(project(":common"))
    implementation(project(":domainA"))
    implementation(project(":domainB"))
    tasks.getByName<BootJar>("bootJar") {
        enabled = true
    }

    tasks.getByName<Jar>("jar") {
        enabled = false
    }
}