package by.mark.orderservice.producer;

import by.mark.orderservice.config.KafkaConfig;
import by.mark.orderservice.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
public class OrderPaymentProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendOrderForPayment(Order order) {
        kafkaTemplate.send(KafkaConfig.PAYMENT_ORDER_QUEUE, order);
    }
}
