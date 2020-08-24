package com.example.myproject.model.dto.request.user;

import lombok.Getter;

@Getter
public class EmailAuthDto {
    private String title = "MyProject 이메일 인증입니다.";

    public String makeText(String userName, String URL) {
        return String.format("%s님의 계정 인증 메일입니다.\n  인증을 계속 하시려면 아래의 링크로 접속해 주세요. \n %s", userName, URL);
    }
}
