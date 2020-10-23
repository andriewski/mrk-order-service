package by.mark.orderservice;

import by.mark.orderservice.container.PgContainer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(initializers = PgContainer.class)
class OrderServiceApplicationTests {

    @Test
    void contextLoads() {

    }

}
