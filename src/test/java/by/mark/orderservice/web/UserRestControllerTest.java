package by.mark.orderservice.web;

import by.mark.orderservice.api.UserService;
import by.mark.orderservice.container.PgContainer;
import by.mark.orderservice.dto.UserDto;
import by.mark.orderservice.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ContextConfiguration(initializers = PgContainer.class)
class UserRestControllerTest extends BaseRestController {

    @Autowired
    private UserService userService;

    @Test
    void createUser() throws Exception {
        mockMvc.perform(post(UserRestController.PATH)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .content(writeValueAsString(new User("mark"))))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("mark"))
                .andExpect(jsonPath("$.id").doesNotExist())
                .andDo(result -> parseResponse(result, UserDto.class))
                .andReturn().getResponse();
    }

    @Test
    void getUserById() throws Exception {
        User mark = userService.createUser(new User("Mark"));

        mockMvc.perform(get(UserRestController.PATH + mark.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(mark.getName()))
                .andExpect(jsonPath("$.id").doesNotExist())
                .andDo(result -> parseResponse(result, UserDto.class))
                .andReturn().getResponse();
    }
}