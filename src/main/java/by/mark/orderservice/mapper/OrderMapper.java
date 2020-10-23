package by.mark.orderservice.mapper;

import by.mark.orderservice.dto.OrderDto;
import by.mark.orderservice.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    Order toOrder(OrderDto orderDto);

    OrderDto toOrderDto(Order order);
}