package com.jpa.projection.controller.post;

import com.jpa.projection.controller.post.request.PostRequest;
import com.jpa.projection.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping()
    public void createInitPost(@RequestBody PostRequest request){
        postService.createInitPost(request.toServiceDto());
    }
}
