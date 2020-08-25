package com.example.myproject.service.auth;

import com.example.myproject.model.dto.request.user.SignUpRequestDto;
import com.example.myproject.repository.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class AuthServiceTest {

    @Autowired
    AuthService authService;

    @Autowired
    UserRepository userRepository;

    @Test
    void signUp() {
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto("Test1234", "password123!", "test@gmail.com");
        authService.signUp(signUpRequestDto);

        Assertions.assertEquals(userRepository.existsByUserName("Test1234"), true);
        Assertions.assertDoesNotThrow(() -> userRepository.findByUserName("Test1234"));
    }

    @Test
    void login() {
    }

    @Test
    void sendMail() {
    }

    @Test
    void mailAuthenticate() {
    }
}