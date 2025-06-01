plugins {
    kotlin("plugin.jpa") version "1.9.25"
}
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}

