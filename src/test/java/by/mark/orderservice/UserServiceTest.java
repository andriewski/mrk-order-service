package by.mark.orderservice;

import by.mark.orderservice.api.UserService;
import by.mark.orderservice.container.PgContainer;
import by.mark.orderservice.mapper.UserMapper;
import by.mark.orderservice.model.User;
import by.mark.orderservice.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(initializers = PgContainer.class)
public class UserServiceTest {

    @SpyBean
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @SpyBean
    UserMapper userMapper;

    @Test
    @Transactional
    public void checkUserCreation() {
        User mark = new User(UUID.randomUUID(), "Mark");
        when(userRepository.findById(any())).thenReturn(Optional.of(mark));
        when(userRepository.save(any())).thenReturn(mark);
        userService.createUser(mark);

        User foundedUser = userService.getUserById(mark.getId());

        assertNotNull(userMapper.toUserDto(foundedUser));
        verify(userMapper, atLeast(1)).toUserDto(foundedUser);
    }

    @Test
    @DisplayName("Testing the number of invocations of a db with configured spring cache")
    void cacheTest() {
        //given:
        User mark = new User(UUID.randomUUID(), "Mark");
        when(userRepository.findById(any())).thenReturn(Optional.of(mark));
        when(userRepository.save(any())).thenReturn(mark);
        userService.createUser(mark);

        //when:
        userService.getUserById(mark.getId());
        userService.getUserById(mark.getId());
        userService.getUserById(mark.getId());
        userService.findUserById(mark.getId());

        //then:
        assertNotNull(userMapper.toUserDto(mark));
        verify(userRepository, times(1)).findById(Mockito.any());
        verify(userService, times(1)).getUserById(Mockito.any());
        verify(userService, times(0)).findUserById(Mockito.any());

        //when:
        userService.removeUserById(mark.getId());
        userService.findUserById(mark.getId());
        userService.findUserById(mark.getId());
        userService.findUserById(mark.getId());
        userService.findUserById(mark.getId());

        //then:
        verify(userRepository, times(2)).findById(Mockito.any());
        verify(userService, times(1)).getUserById(Mockito.any());
        verify(userService, times(1)).findUserById(Mockito.any());
    }
}
