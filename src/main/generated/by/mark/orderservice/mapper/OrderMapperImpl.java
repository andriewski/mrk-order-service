package by.mark.orderservice.mapper;

import by.mark.orderservice.dto.OrderDto;
import by.mark.orderservice.model.Order;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-23T11:57:29+0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.8 (AdoptOpenJDK)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order toOrder(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        Order order = new Order();

        order.setTitle( orderDto.getTitle() );
        order.setUser( orderDto.getUser() );

        return order;
    }

    @Override
    public OrderDto toOrderDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setTitle( order.getTitle() );
        orderDto.setUser( order.getUser() );

        return orderDto;
    }
}
