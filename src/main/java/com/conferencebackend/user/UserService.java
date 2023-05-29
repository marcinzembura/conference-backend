package com.conferencebackend.user;

import com.conferencebackend.exception.LoginAlreadyTakenException;
import com.conferencebackend.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByLogin(String login) {
        try {
            return userRepository.findByLogin(login);
        } catch (UserNotFoundException exception) {
            return null;
        }
    }

    public void updateUserEmail(String login, String newEmail) {
        User user = getUserByLogin(login);
        if (user == null) {
            throw new UserNotFoundException(login);
        }
        user.setEmail(newEmail);
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(String login, String email) {
        User existingUser = userRepository.findByLogin(login);
        if (existingUser != null) {
            if (! existingUser.getEmail().equals(email)) {
                throw new LoginAlreadyTakenException(login);
            }
            return existingUser;
        }
        User newUser = new User(login, email);
        return userRepository.save(newUser);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void addUsersToDataBase() {
        try {
            createUser("john123", "john@example.com");
            createUser("emma456", "emma@example.com");
            createUser("alex789", "alex@example.com");
            createUser("sarah321", "sarah@example.com");
            createUser("michael654", "michael@example.com");
            createUser("michael654", "michael@sa.com");
        } catch (LoginAlreadyTakenException e) {
            System.out.println("Podany login jest już zajęty");
        }
    }
}
