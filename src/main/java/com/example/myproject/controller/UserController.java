package com.example.myproject.controller;

import com.example.myproject.model.dto.resonse.user.UserInfoDto;
import com.example.myproject.service.auth.AuthService;
import com.example.myproject.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final AuthService authService;
    private final UserService userService;

    @GetMapping("/authenticate")
    public void EmailAuthenticate() {
        authService.sendMail();
    }

    @GetMapping("/myInfo")
    public UserInfoDto userInfo() {
        return userService.getUserInfo();
    }
}
