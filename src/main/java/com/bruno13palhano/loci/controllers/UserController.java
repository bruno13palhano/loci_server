package com.bruno13palhano.loci.controllers;

import com.bruno13palhano.User;
import com.bruno13palhano.data.service.UserService;
import com.bruno13palhano.loci.auth.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
@CrossOrigin
public class UserController {
    private final String CODE = "CODE";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(path = "/user/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        User user = userService.getByUsername(username);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = null;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest()
                    .header(HttpHeaders.AUTHORIZATION, "")
                    .header(CODE, "")
                    .body("");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.createToken(authentication);

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, token)
                .header(CODE, "OK")
                .body(token);
    }

    @PostMapping(path = "/authenticated")
    public ResponseEntity<Boolean> authenticated(@RequestBody String token) {
        return new ResponseEntity<>(jwtTokenProvider.validateToken(token.replace("\"", "")),
                HttpStatus.OK);
    }

    @PostMapping(path = "/insert")
    public ResponseEntity<?> insert(@RequestBody User user) {
        if (isUserParamValid(user)) {
            return ResponseEntity.badRequest()
                    .header(CODE, "Invalid fields")
                    .body(null);
        }

        if (userService.usernameAlreadyExist(user.getUsername())) {
            return ResponseEntity.badRequest()
                    .header(CODE, "Username already exists")
                    .body(null);
        }

        if (userService.emailAlreadyExist(user.getEmail())) {
            return ResponseEntity.badRequest()
                    .header(CODE, "Email already exists")
                    .body(null);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        user.setEnabled(true);

        userService.insert(user);

        return ResponseEntity.ok()
                .header(CODE, "OK")
                .body(userService.getByUsername(user.getUsername()));
    }

    private boolean isUserParamValid(User user) {
        return user == null || user.getUsername().isBlank() || user.getEmail().isBlank()
                || user.getPassword().isBlank() || user.getTimestamp().isBlank();
    }
}
