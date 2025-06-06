plugins {
    `java-library`
  id("org.graalvm.buildtools.native") version "0.10.4"    
}

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor(libs.lombok)
    compileOnly(libs.lombok)
    annotationProcessor(platform(libs.micronaut.platform))
    annotationProcessor(libs.micronaut.sourcegen.generator.java)
    annotationProcessor(libs.micronaut.inject.java)
    implementation(platform(libs.micronaut.platform))
    implementation(libs.micronaut.sourcegen.annotations)
    implementation(libs.micronaut.inject)
    testAnnotationProcessor(platform(libs.micronaut.platform))
    testAnnotationProcessor(libs.micronaut.inject.java)
    testImplementation(libs.micronaut.test.junit5)
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
