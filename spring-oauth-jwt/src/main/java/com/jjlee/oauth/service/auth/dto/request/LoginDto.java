package com.jjlee.oauth.service.auth.dto.request;

import com.jjlee.oauth.domain.user.UserSocialType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginDto {

    @NotBlank(message = "{auth.token.notBlank}")
    private String token;

    @NotNull(message = "{user.socialType.notNull}")
    private UserSocialType socialType;

    public static LoginDto of(String token, UserSocialType socialType) {
        return new LoginDto(token, socialType);
    }
}