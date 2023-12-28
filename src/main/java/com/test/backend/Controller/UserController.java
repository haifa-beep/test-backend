package com.test.backend.Controller;


import com.test.backend.entities.DTO.AuthResponseDto;
import com.test.backend.entities.DTO.LoginDto;
import com.test.backend.entities.User;
import com.test.backend.services.impl.UserService;
import com.test.backend.services.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        String response = userService.addUser(user);
        if (response.equals("User registered successfully!")) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        User user = userService.getUser(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        AuthResponseDto authResponse = userService.login(loginDto);
        if (authResponse != null) {
            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}

