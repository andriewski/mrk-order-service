package by.mark.orderservice.autoconfigure;

import by.mark.orderservice.config.CacheConfig;
import by.mark.orderservice.repository.UserRepository;
import by.mark.orderservice.api.UserService;
import by.mark.orderservice.repository.OrderRepository;
import by.mark.orderservice.api.OrderService;
import by.mark.orderservice.service.OrderServiceImpl;
import by.mark.orderservice.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(CacheConfig.class)
@Configuration
public class OrderServiceAutoConfiguration {

    @Bean
    UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

    @Bean
    OrderService messageService(OrderRepository messageRepository) {
        return new OrderServiceImpl(messageRepository);
    }
}
