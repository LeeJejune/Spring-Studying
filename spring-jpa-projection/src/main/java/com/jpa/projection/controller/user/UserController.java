package com.jpa.projection.controller.user;

import com.jpa.projection.service.user.UserService;
import com.jpa.projection.service.user.response.UserPostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}/post")
    public UserPostResponse getUserPost(@PathVariable("id") final Long userId) {
        return userService.getUserPost(userId);
    }
}
