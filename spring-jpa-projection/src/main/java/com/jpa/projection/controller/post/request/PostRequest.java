package com.jpa.projection.controller.post.request;

import com.jpa.projection.service.post.request.PostRequestDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostRequest {
    private String title;
    private String description;

    public PostRequestDto toServiceDto(){
        return PostRequestDto.of(title, description);
    }
}
