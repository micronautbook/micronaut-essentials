plugins {
    `java-library`
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
    testAnnotationProcessor(platform(libs.micronaut.platform))
    testAnnotationProcessor(libs.micronaut.inject.java)
    testImplementation(libs.micronaut.test.junit5)
    testImplementation(libs.jsonassert)
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
