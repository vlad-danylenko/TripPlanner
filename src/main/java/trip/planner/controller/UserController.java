package trip.planner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trip.planner.dto.user.UserCredentialsDto;
import trip.planner.dto.user.UserRegistrationDto;
import trip.planner.service.UserService;


import javax.validation.Valid;
import java.util.Optional;
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDto userRegistrationDTO) {
        try {
            userService.registerUser(userRegistrationDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Optional> getUserInfo(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.getUserInfo(userId));
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> authUser(@RequestBody UserCredentialsDto userCredentialsDTO) {
            return ResponseEntity.ok(userService.authUser(userCredentialsDTO));
    }
}
