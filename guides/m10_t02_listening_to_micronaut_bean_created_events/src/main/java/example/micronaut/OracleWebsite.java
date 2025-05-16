package example.micronaut;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.util.StringUtils;
import io.micronaut.sourcegen.annotations.Builder;

@Builder
@Introspected
public record OracleWebsite(String url, String countryRegion) {
    @Override
    public String toString() {
        if (StringUtils.isEmpty(countryRegion)) {
            return url;
        }
        return url + "/" + countryRegion + "/";
    }
}
