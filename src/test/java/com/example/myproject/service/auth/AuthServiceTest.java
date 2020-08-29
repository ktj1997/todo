package com.example.myproject.service.auth;

import com.example.myproject.model.dto.request.user.LoginRequestDto;
import com.example.myproject.model.dto.request.user.SignUpRequestDto;
import com.example.myproject.model.dto.resonse.user.LoginResponseDto;
import com.example.myproject.repository.user.UserRepository;
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
class AuthServiceTest {

    @Autowired
    AuthService authService;

    @Autowired
    EmailAuthFunction emailAuthFunction;

    @Autowired
    UserRepository userRepository;


    @BeforeEach
    void signUpBeforeEach() {
        //given
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto("Test1234", "password123!", "test@gmail.com");
        authService.signUp(signUpRequestDto);

        //then
        Assertions.assertEquals(userRepository.existsByUserName("Test1234"), true);
        Assertions.assertDoesNotThrow(() -> userRepository.findByUserName("Test1234"));
    }

    @Test
    void signUp() {
        //then
        Assertions.assertEquals(userRepository.existsByUserName("Test1234"), true);
        Assertions.assertDoesNotThrow(() -> userRepository.findByUserName("Test1234"));
    }

    @Test
    void login() {
        //given
        LoginRequestDto loginRequestDto = new LoginRequestDto("Test1234", "password123!");

        //then
        LoginResponseDto token = Assertions.assertDoesNotThrow(() -> authService.login(loginRequestDto));
        Assertions.assertNotNull(token);
    }

    @Test
    void sendMail() {

        //given
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("Test1234", "password123!"));

        //then
        Assertions.assertDoesNotThrow(() -> authService.sendMail());


    }

    @Test
    void mailAuthenticate() {
        //given
        String token = emailAuthFunction.createToken("Test1234");
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("Test1234", "password123!"));

        //then
        Assertions.assertNotNull(token);
        Assertions.assertDoesNotThrow(() -> authService.mailAuthenticate(token));
    }
}