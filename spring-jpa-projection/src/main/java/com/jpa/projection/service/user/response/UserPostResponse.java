package com.jpa.projection.service.user.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserPostResponse {
    private Long userId;
    private String title;
    private String description;

    private UserPostResponse(final Long userId, final String title, final String description) {
        this.userId = userId;
        this.title = title;
        this.description = description;
    }

    public static UserPostResponse of(final Long userId, final String title, final String description){
        return new UserPostResponse(userId, title, description);
    }
}
