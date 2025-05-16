package example.micronaut;

import io.micronaut.context.event.ApplicationEvent;

public class OrderEvent extends ApplicationEvent {
    public OrderEvent(Order order) {
        super(order);
    }
}
