plugins {
	kotlin("jvm") version "2.2.21"
	kotlin("kapt") version "2.2.21"
	kotlin("plugin.spring") version "2.2.21"
	kotlin("plugin.jpa") version "2.2.21"
	id("org.springframework.boot") version "4.0.5"
	id("io.spring.dependency-management") version "1.1.7"
	jacoco
}

allOpen {
	annotations(
		"jakarta.persistence.Entity",
		"jakarta.persistence.MappedSuperclass",
		"jakarta.persistence.Embeddable"
	)
}

group = "soia"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// spring
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")

	// database
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("com.mysql:mysql-connector-j")

	// kotlin
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// document
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:3.0.2")

	// test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("com.h2database:h2")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
