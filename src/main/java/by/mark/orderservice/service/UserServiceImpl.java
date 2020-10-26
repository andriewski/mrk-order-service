package by.mark.orderservice.service;

import by.mark.orderservice.api.UserService;
import by.mark.orderservice.config.CacheConfig;
import by.mark.orderservice.model.User;
import by.mark.orderservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Cacheable(cacheNames = CacheConfig.USER_CACHE, key = "#id")
    public User getUserById(UUID id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, User.class.getSimpleName()));
    }

    @Override
    @Cacheable(cacheNames = CacheConfig.USER_CACHE, key = "#id")
    public Optional<User> findUserById(UUID id) {
        return userRepository.findById(id);
    }

    @Transactional
    @CacheEvict(cacheNames = CacheConfig.USER_CACHE, key = "#id")
    @Override
    public void removeUserById(UUID id) {
        userRepository.deleteById(id);
    }
}
