package example.micronaut;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.scheduling.annotation.Async;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

@Singleton
class OrderEventListener implements ApplicationEventListener<OrderEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(OrderEventListener.class);
    @Override
    public void onApplicationEvent(OrderEvent event) {
        if (event.getSource() instanceof Order order) {
            processOrder(order);
        }
    }

    @Async
    void processOrder(Order order) {
        try {
            Thread.sleep(Duration.ofSeconds(15));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LOG.info("{} Amount: {}", order.dateCreated(), order.amount());
    }
}
