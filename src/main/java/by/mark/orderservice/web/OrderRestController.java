package by.mark.orderservice.web;

import by.mark.orderservice.api.OrderService;
import by.mark.orderservice.dto.OrderDto;
import by.mark.orderservice.mapper.OrderMapper;
import by.mark.orderservice.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping(OrderRestController.PATH)
@RestController
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public static final String PATH = "/api/v1/order";

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable UUID id) {
        return orderMapper.toOrderDto(orderService.getOrderById(id));
    }

    @PostMapping
    public OrderDto createOrder(@Valid @RequestBody OrderDto orderDto) {
        Order order = orderMapper.toOrder(orderDto);
        Order createdOrder = orderService.createOrder(order);

        return orderMapper.toOrderDto(createdOrder);
    }
}
