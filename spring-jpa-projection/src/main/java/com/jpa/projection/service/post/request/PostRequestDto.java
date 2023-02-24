package com.jpa.projection.service.post.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostRequestDto {
    private String title;
    private String description;

    private PostRequestDto(final String title, final String description) {
        this.title = title;
        this.description = description;
    }

    public static PostRequestDto of(final String title, final String description){
        return new PostRequestDto(title, description);
    }
}
