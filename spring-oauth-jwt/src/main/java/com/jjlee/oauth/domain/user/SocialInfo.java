package com.jjlee.oauth.domain.user;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Embeddable
public class SocialInfo {

    @Column(length = 200, nullable = false)
    private String socialId;

    @Column(length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserSocialType socialType;

    private SocialInfo(String socialId, UserSocialType socialType) {
        this.socialId = socialId;
        this.socialType = socialType;
    }

    public static SocialInfo of(String socialId, UserSocialType socialType) {
        return new SocialInfo(socialId, socialType);
    }
}
