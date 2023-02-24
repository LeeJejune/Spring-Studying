package com.jpa.projection.service.user;

import com.jpa.projection.Exception.NotFoundException;
import com.jpa.projection.domain.user.UserRepository;
import com.jpa.projection.service.user.response.UserPostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public UserPostResponse getUserPost(final Long userId){
        final var userPost = userRepository.findUserPost(userId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 유저 입니다."));

        return UserPostResponse.of(userPost.getId(), userPost.getTitle(), userPost.getDescription());

    }

}
