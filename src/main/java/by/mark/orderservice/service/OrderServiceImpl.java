package by.mark.orderservice.service;

import by.mark.orderservice.api.OrderService;
import by.mark.orderservice.config.CacheConfig;
import by.mark.orderservice.model.Order;
import by.mark.orderservice.producer.OrderPaymentProducer;
import by.mark.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderPaymentProducer orderPaymentProducer;

    @Transactional
    public Order createOrder(Order order) {
        Order createdOrder = orderRepository.save(order);
        orderPaymentProducer.sendOrderForPayment(createdOrder);

        return createdOrder;
    }

    @Cacheable(cacheNames = CacheConfig.ORDER_CACHE, key = "#id")
    public Order getOrderById(UUID id) {
        return orderRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Order.class.getSimpleName()));
    }

    @CacheEvict(cacheNames = CacheConfig.ORDER_CACHE, key = "#id")
    public void removeOrderById(UUID id) {
        orderRepository.deleteById(id);
    }
}
