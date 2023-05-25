package com.conferencebackend.user;

import com.conferencebackend.exception.UserNotFoundException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public void updateUserEmail(String login, String newEmail) {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UserNotFoundException(login);
        }
        user.setEmail(newEmail);
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void addUsersToDataBase() {
        User user1 = new User("john123","john@example.com");
        User user2 = new User("emma456", "emma@example.com");
        User user3 = new User("alex789", "alex@example.com");
        User user4 = new User("sarah321", "sarah@example.com");
        User user5 = new User("michael654",  "michael@example.com");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
    }
}
