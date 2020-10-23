package by.mark.orderservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@ComponentScan("by.mark")
@SpringBootTest
@AutoConfigureMockMvc
public abstract class BaseRestController {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @SuppressWarnings({"UnusedReturnValue", "SameParameterValue"})
    @SneakyThrows
    protected <T> T parseResponse(MvcResult mvcResult, Class<T> clazz) {
        return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), clazz);
    }

    @SneakyThrows
    protected String writeValueAsString(Object o) {
        return objectMapper.writeValueAsString(o);
    }
}
