package example.micronaut;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.ResponseFilter;
import io.micronaut.http.annotation.ServerFilter;
import io.micronaut.http.filter.FilterPatternStyle;

@ServerFilter(
        patternStyle = FilterPatternStyle.REGEX,
        value = "^/assets/bootstrap.*$"
)
class CacheControlFilter {
    @ResponseFilter
    public void filterResponse(MutableHttpResponse<?> rsp) {
        if (!rsp.getHeaders().contains(HttpHeaders.CACHE_CONTROL)) {
            rsp.getHeaders().add(HttpHeaders.CACHE_CONTROL,
                    "public, max-age=31536000, immutable");
        }
    }
}
