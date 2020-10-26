package by.mark.orderservice.producer;

import by.mark.orderservice.container.PgContainer;
import by.mark.orderservice.model.Order;
import by.mark.orderservice.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
@ContextConfiguration(initializers = PgContainer.class)
class OrderPaymentProducerTest {

    @Autowired
    OrderPaymentProducer orderPaymentProducer;

    @Test
    public void checkSending() throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            try {
                orderPaymentProducer.sendOrderForPayment(
                        Order.builder()
                                .id(UUID.randomUUID())
                                .title("check 1")
                                .price(0.01)
                                .user(new User(UUID.randomUUID(), "Mark"))
                                .build()
                );
            } catch (Exception e) {
                log.error("An error has been occurred during sending message", e);
            }
        }, 1, 2, TimeUnit.SECONDS);

        TimeUnit.MINUTES.sleep(10);
    }
}