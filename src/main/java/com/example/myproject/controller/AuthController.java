package com.example.myproject.controller;

import com.example.myproject.model.dto.request.user.LoginRequestDto;
import com.example.myproject.model.dto.request.user.SignUpRequestDto;
import com.example.myproject.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signUp")
    public void signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        authService.signUp(signUpRequestDto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }

    @GetMapping("/validate")
    public void validateMail(@RequestParam String token) {
        authService.mailAuthenticate(token);
    }

}
