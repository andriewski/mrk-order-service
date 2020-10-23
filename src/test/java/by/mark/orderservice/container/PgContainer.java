package by.mark.orderservice.container;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;


public class PgContainer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    public static final String IMAGE = "postgres:12.3";

    private final PostgreSQLContainer<?> pgContainer = new PostgreSQLContainer<>(IMAGE)
            .withReuse(true)
            .withUsername("user")
            .withPassword("pass");

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        pgContainer.start();

        TestPropertyValues.of(
                "spring.datasource.url=" + pgContainer.getJdbcUrl()
        )
                .applyTo(applicationContext.getEnvironment());
    }
}
