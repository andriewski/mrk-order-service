package by.mark.orderservice.web;

import by.mark.orderservice.api.UserService;
import by.mark.orderservice.dto.UserDto;
import by.mark.orderservice.mapper.UserMapper;
import by.mark.orderservice.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping(UserRestController.PATH)
@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;
    private final UserMapper userMapper;

    public static final String PATH = "/api/v1/user/";

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto getUserById(@PathVariable UUID id) {
        return userMapper.toUserDto(userService.getUserById(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto createUser(@RequestBody UserDto userDto) {
        User order = userMapper.toUser(userDto);
        User createdUser = userService.createUser(order);

        return userMapper.toUserDto(createdUser);
    }
}