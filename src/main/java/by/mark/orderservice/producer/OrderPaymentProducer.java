package by.mark.orderservice.producer;

import by.mark.event.CreatePaymentEvent;
import by.mark.orderservice.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

import static by.mark.orderservice.config.KafkaConfig.PAYMENT_ORDER_QUEUE;

@Slf4j
@RequiredArgsConstructor
public class OrderPaymentProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendOrderForPayment(Order order) {
        CreatePaymentEvent event = CreatePaymentEvent.builder()
                .orderId(order.getId())
                .userId(order.getUser().getId())
                .price(order.getPrice())
                .title(order.getTitle())
                .build();

        log.debug("Sending order {} to {}", PAYMENT_ORDER_QUEUE, event);
        kafkaTemplate.send(PAYMENT_ORDER_QUEUE, event.getOrderId().toString(), event);
    }
}
