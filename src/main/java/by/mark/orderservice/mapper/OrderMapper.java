package by.mark.orderservice.mapper;

import by.mark.orderservice.dto.OrderDto;
import by.mark.orderservice.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    @Mapping(source = "userId", target = "user.id")
    Order toOrder(OrderDto orderDto);

    @Mapping(source = "user.id", target = "userId")
    OrderDto toOrderDto(Order order);
}