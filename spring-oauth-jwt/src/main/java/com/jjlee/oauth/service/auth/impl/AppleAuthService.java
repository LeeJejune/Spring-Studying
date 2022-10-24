package com.jjlee.oauth.service.auth.impl;

import com.jjlee.oauth.domain.user.UserRepository;
import com.jjlee.oauth.domain.user.UserSocialType;
import com.jjlee.oauth.external.client.apple.AppleTokenDecoder;
import com.jjlee.oauth.service.auth.AuthService;
import com.jjlee.oauth.service.auth.dto.request.LoginDto;
import com.jjlee.oauth.service.auth.dto.request.SignUpDto;
import com.jjlee.oauth.service.user.UserService;
import com.jjlee.oauth.service.user.UserServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppleAuthService implements AuthService {

    private static final UserSocialType socialType = UserSocialType.APPLE;
    private final UserRepository userRepository;

    private final AppleTokenDecoder appleTokenDecoder;

    private final UserService userService;
    @Override
    public Long signUp(SignUpDto request) {
        String socialId = appleTokenDecoder.getSocialIdFromIdToken(request.getToken());
        return userService.registerUser(request.toCreateUserDto(socialId));
    }

    @Override
    public Long login(LoginDto request) {
        String socialId = appleTokenDecoder.getSocialIdFromIdToken(request.getToken());
        return UserServiceUtils.findUserBySocialIdAndSocialType(userRepository, socialId, socialType).getId();
    }
}

