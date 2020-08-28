package com.example.myproject.service.auth;

import com.example.myproject.exception.*;
import com.example.myproject.model.dto.request.user.LoginRequestDto;
import com.example.myproject.model.dto.request.user.SignUpRequestDto;
import com.example.myproject.model.dto.resonse.user.LoginResponseDto;
import com.example.myproject.model.dto.resonse.user.RefreshTokenDto;
import com.example.myproject.model.entity.user.User;
import com.example.myproject.model.entity.user.UserAuthority;
import com.example.myproject.repository.user.UserRepository;
import com.example.myproject.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByUserName(loginRequestDto.getUserName()).orElseThrow(UserNotExistException::new);

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) //비밀번호가 같지 않을 때
            throw new InvalidUserInfoException();

        return LoginResponseDto.builder()
                .accessToken(jwtProvider.createAccessToken(user.getUsername(), user.getRoles()))
                .refreshToken(jwtProvider.createRefreshToken(user.getUsername(), user.getRoles()))
                .build();
    }

    @Transactional
    public String refreshAccessToken(RefreshTokenDto refreshTokenDto) {
        if (jwtProvider.validateToken(refreshTokenDto.getRefreshToken()))
            return jwtProvider.createAccessToken(jwtProvider.getUserId(refreshTokenDto.getRefreshToken()), jwtProvider.getAuthorities(refreshTokenDto.getRefreshToken()));

        throw new RefreshTokenInvalidException();
    }

     @Transactional
    public void sendMail() {
        User user = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(UserNotExistException::new);
        if (user.getIsAuthenticate())
            throw new AlreadyAuthenticatedException();

        emailAuthFunction.mailSend(user);
    }

    @Transactional
    public void mailAuthenticate(String token) {
        if (emailAuthFunction.validateToken(token)) {
            User user = userRepository.findByUserName(jwtProvider.getUserId(token)).orElseThrow(UserNotExistException::new);
            user.setIsAuthenticate(true);
            user.setRoles(Collections.singletonList(UserAuthority.ROLE_USER.toString()));
        } else
            throw new AuthenticateFailedException();
    }
}
