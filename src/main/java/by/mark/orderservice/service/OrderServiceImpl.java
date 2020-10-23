package by.mark.orderservice.service;

import by.mark.orderservice.api.OrderService;
import by.mark.orderservice.config.CacheConfig;
import by.mark.orderservice.model.Order;
import by.mark.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.UUID;

@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
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
