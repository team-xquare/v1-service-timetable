plugins {
    id("org.springframework.boot") version "2.7.6"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2021.0.5")
    }
}

dependencies {
    // Web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Validation
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Security
    implementation("org.springframework.boot:spring-boot-starter-security")

    // JPA
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // MySQL
    runtimeOnly("mysql:mysql-connector-java")

    // Cloud Config
    implementation("org.springframework.cloud:spring-cloud-starter-config")

    // Open Feign
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

    // Jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation(project(":timetable-application"))
}

tasks.getByName<Jar>("jar") {
    enabled = false
}

allOpen {
    annotation("javax.persistence.Entity")
}

noArg {
    annotation("javax.persistence.Entity")
}
