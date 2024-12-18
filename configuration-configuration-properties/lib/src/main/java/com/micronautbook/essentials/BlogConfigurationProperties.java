package com.micronautbook.essentials;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("blog")
public class BlogConfigurationProperties implements BlogConfiguration {
    private Integer homePosts = 5;

    public Integer getHomePosts() {
        return homePosts;
    }

    public void setHomePosts(Integer homePosts) {
        this.homePosts = homePosts;
    }
}
