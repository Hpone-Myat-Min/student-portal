package uk.ac.leedsbeckett.hmm.student_portal.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.ac.leedsbeckett.hmm.student_portal.entities.Role;
import uk.ac.leedsbeckett.hmm.student_portal.entities.User;
import uk.ac.leedsbeckett.hmm.student_portal.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        // Constructor Injection
        this.userService = userService;
    }

    @PostMapping("/registerUser")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User newUser = userService.registerUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/loginUser")
    public ResponseEntity<User> loginUser(@RequestParam String username, @RequestParam String password) {
        User newUser = userService.loginUser(username, password);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @GetMapping("/checkRole/{id}")
    public ResponseEntity<String> checkRole(@PathVariable Long id) {
        return new ResponseEntity<>(userService.checkRole(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
