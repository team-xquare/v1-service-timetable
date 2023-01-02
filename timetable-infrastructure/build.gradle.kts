plugins {
    id("org.springframework.boot") version "2.7.6"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation(project(":timetable-application"))
}

tasks.getByName<Jar>("jar") {
    enabled = false
}
