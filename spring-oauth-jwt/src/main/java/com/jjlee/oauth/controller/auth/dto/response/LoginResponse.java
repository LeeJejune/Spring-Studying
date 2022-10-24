package com.jjlee.oauth.controller.auth.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.NoArgsConstructor;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponse {
    private String token;

    private Long userId;

    public static LoginResponse of(String token, Long userId) {
        return new LoginResponse(token, userId);
    }
}
