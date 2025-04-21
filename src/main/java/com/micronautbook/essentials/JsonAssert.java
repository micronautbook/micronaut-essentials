package com.micronautbook.essentials;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.starter.application.ApplicationType;
import io.micronaut.starter.application.generator.GeneratorContext;
import io.micronaut.starter.build.dependencies.Dependency;
import io.micronaut.starter.feature.Feature;
import jakarta.inject.Singleton;

@Singleton
class JsonAssert implements Feature {
    private static final Dependency JSONASSERT = Dependency.builder()
            .groupId("org.skyscreamer")
            .artifactId("jsonassert")
            .version("1.5.3")
            .test()
            .build();

    @Override
    public @NonNull String getName() {
        return "jsonassert";
    }

    @Override
    public boolean supports(ApplicationType applicationType) {
        return true;
    }

    @Override
    public void apply(GeneratorContext generatorContext) {
        generatorContext.addDependency(JSONASSERT);
    }
}
