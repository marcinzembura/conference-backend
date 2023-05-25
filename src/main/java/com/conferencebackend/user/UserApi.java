package com.conferencebackend.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserApi {

    private final UserService userService;

    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{login}")
    public ResponseEntity<User> getUserByLogin(@PathVariable String login) {
        User user = userService.getUserByLogin(login);//add throw
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{login}")
    public ResponseEntity<Void> updateUserEmail(@PathVariable String login, @RequestParam String newEmail) {
        userService.updateUserEmail(login, newEmail);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
