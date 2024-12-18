plugins {
    `java-library`
  id("org.graalvm.buildtools.native") version "0.10.4"    
}

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor(platform(libs.micronaut.platform))
    annotationProcessor(libs.micronaut.inject.java)
    implementation(platform(libs.micronaut.platform))
    annotationProcessor(libs.micronaut.serde.processor)
    implementation(libs.micronaut.serde.jackson)

    implementation(libs.micronaut.problem.json)

    annotationProcessor(libs.micronaut.validation.processor)
    implementation(libs.micronaut.validation)

    testAnnotationProcessor(platform(libs.micronaut.platform))
    testAnnotationProcessor(libs.micronaut.validation.processor)
    testAnnotationProcessor(libs.micronaut.inject.java)
    testImplementation(libs.micronaut.test.junit5)
    testImplementation(libs.micronaut.http.client)
    testImplementation(libs.micronaut.http.server.netty)
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter("5.10.3")
        }
    }
}
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
