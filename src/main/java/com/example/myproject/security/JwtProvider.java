package com.example.myproject.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    @Value(value = "${myproject.jwt.serect}")
    private String secretKey;

    private Long tokenValidTime = 30 * 24 * 60 * 60 * 1000L; //일단 30일 -->추후에 RefreshToken 구현시에 손볼 것.


    private final CustomUserDetailService userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String userId, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userId); //jwt payload에 저장 할 것
        claims.put("roles", roles);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /*
        JWT토큰으로부터 Authentication토큰을 가져오는 작업
     */
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities()); //principal,credentials,authorities
    }

    /*
        JWT토큰으로부터 User의 PrimaryKey를 가져오는 작업
     */
    public String getUserId(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    /*
        요청의 Header에서 X-AUTH-TOKEN부분을 가져옴
     */
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    /*
        토큰의 유효성 검사
     */
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date()); //서버에서 정상 발행 된 것 인증 + 만료되지않음 == 통과
        } catch (Exception e) {
            return false;
        }
    }

}
