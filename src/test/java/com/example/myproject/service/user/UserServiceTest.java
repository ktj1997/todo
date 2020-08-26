package com.example.myproject.service.user;

import com.example.myproject.model.dto.request.user.SignUpRequestDto;
import com.example.myproject.model.dto.resonse.user.UserInfoDto;
import com.example.myproject.service.auth.AuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @BeforeEach
    void UserTestBeforeEach() {
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto("Test1234", "password123!", "test@gmail.com");
        authService.signUp(signUpRequestDto);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("Test1234", "password123!"));
    }

    @Test
    void getUserInfo() {
        UserInfoDto userInfoDto = Assertions.assertDoesNotThrow(() -> userService.getUserInfo());
        Assertions.assertNotNull(userInfoDto);
    }
}