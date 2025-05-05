package com.micronautbook.essentials;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.starter.application.ApplicationType;
import io.micronaut.starter.application.generator.GeneratorContext;
import io.micronaut.starter.build.dependencies.Dependency;
import io.micronaut.starter.feature.Feature;
import jakarta.inject.Singleton;

@Singleton
class Vcard implements Feature {
    private static final Dependency VCARD = Dependency.builder()
            .groupId("com.googlecode.ez-vcard")
            .artifactId("ez-vcard")
            .version("0.12.1")
            .test()
            .build();

    @Override
    public @NonNull String getName() {
        return "vcard";
    }

    @Override
    public boolean supports(ApplicationType applicationType) {
        return true;
    }

    @Override
    public void apply(GeneratorContext generatorContext) {
        generatorContext.addDependency(VCARD);
    }
}
