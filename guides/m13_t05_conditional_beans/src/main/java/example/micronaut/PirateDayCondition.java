package example.micronaut;

import io.micronaut.context.condition.Condition;
import io.micronaut.context.condition.ConditionContext;
import java.time.LocalDate;
import java.time.Month;

public class PirateDayCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context) {
        DateProvider dateProvider = context.getBeanContext().getBean(DateProvider.class);
        LocalDate date = dateProvider.localDate();
        return date.getMonth() == Month.SEPTEMBER && date.getDayOfMonth() == 19;
    }
}
