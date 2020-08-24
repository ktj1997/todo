package com.example.myproject.service.auth;

import com.example.myproject.exception.UserNotExistException;
import com.example.myproject.model.dto.request.user.EmailAuthDto;
import com.example.myproject.model.entity.user.User;
import com.example.myproject.repository.user.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class EmailAuthFunction {

    @Value("${myproject.jwt.serect}")
    private String secretKey;
    private Long tokenValidTime = 5 * 60 * 1000L; //유효기간 5분
    private static final String BaseURL = "https://my--project.herokuapp.com/auth/validate?token=";
    private static final String MailSender = "MyProject@gmail.com";
    private EmailAuthDto emailAuthDto = new EmailAuthDto();

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;

    public void mailSend(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        String token = createToken(user.getUsername());

        message.setTo(user.getEmail());
        message.setFrom(MailSender);
        message.setSubject(emailAuthDto.getTitle());
        message.setText(emailAuthDto.makeText(user.getUsername(), BaseURL + token));

        javaMailSender.send(message);
    }

    private String createToken(String userId) {
        Claims claims = Jwts.claims().setSubject(userId); //jwt payload에 저장 할 것
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date()); //서버에서 정상 발행 된 것 인증 + 만료되지않음 == 통과
        } catch (Exception e) {
            return false;
        }
    }
}
