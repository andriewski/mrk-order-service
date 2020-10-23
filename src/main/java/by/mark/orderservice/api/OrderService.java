package by.mark.orderservice.api;

import by.mark.orderservice.model.Order;

import java.util.UUID;

public interface OrderService {

    Order createOrder(Order order);

    Order getOrderById(UUID id);

    void removeOrderById(UUID id);
}
