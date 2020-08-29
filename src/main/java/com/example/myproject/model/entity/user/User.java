package com.example.myproject.model.entity.user;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;

    private String password;

    private String Email;

    @Setter
    private Boolean isAuthenticate = false;

    @Setter
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> roles = new ArrayList<String>();

    @Builder
    public User(String userName, String password, List<String> roles, String Email) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
        this.Email = Email;
    }
    /*
        String으로 저장되어있는 권한목록을 GrantedAuthority 객체로 다시 바꿔서 리턴
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
