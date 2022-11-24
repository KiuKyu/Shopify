package com.shopify.controller;

import com.shopify.config.JwtProvider;
import com.shopify.model.dto.CustomResponse;
import com.shopify.model.dto.JwtResponse;
import com.shopify.model.dto.LoginForm;
import com.shopify.model.dto.RegisterForm;
import com.shopify.model.persistence.user.User;
import com.shopify.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private IUserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm) {
        User currentUser = userService.findByUsername(loginForm.getUsername());
        if (currentUser == null) {
            return new ResponseEntity<>(new CustomResponse("Username không tồn tại"), HttpStatus.BAD_REQUEST);
        } else {
            boolean matchPassword = passwordEncoder.matches(loginForm.getPassword(), currentUser.getPassword());
            if (!matchPassword) {
                CustomResponse message = new CustomResponse("Mật khẩu không đúng");
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            } else {
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(currentUser.getUsername(), loginForm.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtProvider.generateTokenLogin(authentication);
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                return new ResponseEntity<>(new JwtResponse(jwt, currentUser.getId(), userDetails.getAuthorities()), HttpStatus.OK);
            }
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterForm registerForm) {
        boolean confirmPasswordMatch = registerForm.getConfirmPassword().equals(registerForm.getPassword());
        if (!confirmPasswordMatch) {
            return new ResponseEntity<>(new CustomResponse("Mật khẩu không trùng khớp"), HttpStatus.BAD_REQUEST);
        } else {
            User newUser = new User(registerForm.getUsername(), registerForm.getPassword());
            userService.saveUser(newUser, "ROLE_USER");
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
    }
}
