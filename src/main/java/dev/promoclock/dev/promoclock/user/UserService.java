package dev.promoclock.user;

import dev.promoclock.dev.promoclock.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends User {

    @Autowired
    private dev.promoclock.user.UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }


}