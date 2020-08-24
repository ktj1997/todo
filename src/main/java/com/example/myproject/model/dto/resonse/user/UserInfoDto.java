package com.example.myproject.model.dto.resonse.user;

import com.example.myproject.model.entity.user.User;
import lombok.Getter;

@Getter
public class UserInfoDto {
    String userName;
    String Email;
    Boolean isAuthenticate;

    public UserInfoDto(User user) {
        this.userName = user.getUsername();
        this.Email = user.getEmail();
        this.isAuthenticate = user.getIsAuthenticate();
    }
}
