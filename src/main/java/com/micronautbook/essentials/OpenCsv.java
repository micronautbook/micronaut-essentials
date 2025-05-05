package com.micronautbook.essentials;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.starter.application.ApplicationType;
import io.micronaut.starter.application.generator.GeneratorContext;
import io.micronaut.starter.build.dependencies.Dependency;
import io.micronaut.starter.feature.Feature;
import jakarta.inject.Singleton;

@Singleton
class OpenCsv implements Feature {
    private static final Dependency OPENCSV = Dependency.builder()
            .groupId("com.opencsv")
            .artifactId("opencsv")
            .version("5.9")
            .test()
            .build();

    @Override
    public @NonNull String getName() {
        return "opencsv";
    }

    @Override
    public boolean supports(ApplicationType applicationType) {
        return true;
    }

    @Override
    public void apply(GeneratorContext generatorContext) {
        generatorContext.addDependency(OPENCSV);
    }
}
