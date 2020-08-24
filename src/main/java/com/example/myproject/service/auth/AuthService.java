package com.example.myproject.service.auth;

import com.example.myproject.exception.*;
import com.example.myproject.model.dto.request.user.LoginRequestDto;
import com.example.myproject.model.dto.request.user.SignUpRequestDto;
import com.example.myproject.model.entity.user.User;
import com.example.myproject.model.entity.user.UserAuthority;
import com.example.myproject.repository.user.UserRepository;
import com.example.myproject.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final EmailAuthFunction emailAuthFunction;

    @Transactional
    public void signUp(SignUpRequestDto signUpRequestDto) {
        if (userRepository.existsByUserName(signUpRequestDto.getUserName()))
            throw new UserAlreadyExistException();

        userRepository.save(User.builder()
                .userName(signUpRequestDto.getUserName())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .roles(Collections.singletonList(UserAuthority.ROLE_GUEST.toString())) //Collections.singletonList는 불변성을 보장하고, 메모리에서 이점을 가질 수 있다.
                .Email(signUpRequestDto.getEmail()).build());
    }

    @Transactional
    public String login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByUserName(loginRequestDto.getUserName()).orElseThrow(UserNotExistException::new);

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) //비밀번호가 같지 않을 때
            throw new InvalidUserInfoException();

        return jwtProvider.createToken(user.getUsername(), user.getRoles());

    }

    @Transactional
    public void sendMail() {
        User user = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(UserNotExistException::new);
        if (user.getIsAuthenticate())
            throw new AlreadyAuthenticatedException();

        emailAuthFunction.mailSend();
    }

    @Transactional
    public void mailAuthenticate(String token) {
        if (emailAuthFunction.validateToken(token)) {
            User user = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(UserNotExistException::new);
            user.setIsAuthenticate(true);
            user.setRoles(Collections.singletonList(UserAuthority.ROLE_USER.toString()));
        }
        throw new AuthenticateFailedException();
    }

}
