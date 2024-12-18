package com.micronautbook.essentials;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.bind.annotation.Bindable;

@ConfigurationProperties("blog")
public interface BlogConfiguration {
    @Bindable(defaultValue = "10")
    Integer getHomePosts();
}
