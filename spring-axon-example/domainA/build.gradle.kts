import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    implementation(project(":common"))
    tasks.getByName<BootJar>("bootJar") {
        enabled = false
    }

    tasks.getByName<Jar>("jar") {
        enabled = true
    }
}