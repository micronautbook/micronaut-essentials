package example.micronaut;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.bind.annotation.Bindable;

@ConfigurationProperties("blog")
public interface BlogConfiguration {
    @Bindable(defaultValue = "5")
    Integer getPostsPerPage();
}
