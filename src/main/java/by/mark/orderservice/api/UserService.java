package by.mark.orderservice.api;

import by.mark.orderservice.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    User createUser(User user);

    User getUserById(UUID id);

    Optional<User> findUserById(UUID id);

    void removeUserById(UUID id);
}
