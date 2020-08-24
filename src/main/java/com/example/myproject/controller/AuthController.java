package com.example.myproject.controller;

import com.example.myproject.model.dto.request.user.LoginRequestDto;
import com.example.myproject.model.dto.request.user.SignUpRequestDto;
import com.example.myproject.service.auth.AuthService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @ApiOperation("회원가입")
    @PostMapping("/signUp")
    public void signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        authService.signUp(signUpRequestDto);
    }

    @ApiOperation("로그인")
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }

    @ApiOperation("이메일 인증 유효성 검사")
    @GetMapping("/validate")
    public void validateMail(@RequestParam String token) {
        authService.mailAuthenticate(token);
    }

}
