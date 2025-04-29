package example.micronaut;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Context;
import jakarta.validation.constraints.PositiveOrZero;

@Context
@ConfigurationProperties("company")
public class CompanyConfiguration {
    @PositiveOrZero
    private Integer valueAddedTax;

    public Integer getValueAddedTax() {
        return valueAddedTax;
    }

    public void setValueAddedTax(Integer valueAddedTax) {
        this.valueAddedTax = valueAddedTax;
    }
}
