package by.mark.orderservice.producer;

import by.mark.orderservice.container.PgContainer;
import by.mark.orderservice.model.Order;
import by.mark.orderservice.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.UUID;

@SpringBootTest
@ContextConfiguration(initializers = PgContainer.class)
class OrderPaymentProducerTest {

    @Autowired
    OrderPaymentProducer orderPaymentProducer;

    @Test
    public void checkSending() {
        orderPaymentProducer.sendOrderForPayment(
                Order.builder()
                        .id(UUID.randomUUID())
                        .title("check 1")
                        .user(new User(UUID.randomUUID(), "Mark"))
                        .build()
        );
    }
}