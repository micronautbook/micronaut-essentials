package example.micronaut;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("blog")
public class BlogConfiguration {
    private Integer postsPerPage = 5;

    public void setPostsPerPage(Integer postsPerPage) {
        this.postsPerPage = postsPerPage;
    }

    public Integer getPostsPerPage() {
        return postsPerPage;
    }
}
