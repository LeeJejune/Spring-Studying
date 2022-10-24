package com.jjlee.oauth.service.auth.impl;

import com.jjlee.oauth.common.util.HttpHeaderUtils;
import com.jjlee.oauth.domain.user.UserRepository;
import com.jjlee.oauth.domain.user.UserSocialType;
import com.jjlee.oauth.external.client.google.GoogleAuthApiClient;
import com.jjlee.oauth.external.client.google.dto.response.GoogleProfileInfoResponse;
import com.jjlee.oauth.service.auth.AuthService;
import com.jjlee.oauth.service.auth.dto.request.LoginDto;
import com.jjlee.oauth.service.auth.dto.request.SignUpDto;
import com.jjlee.oauth.service.user.UserService;
import com.jjlee.oauth.service.user.UserServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GoogleAuthService implements AuthService {

    private final UserSocialType socialType = UserSocialType.GOOGLE;
    private final UserRepository userRepository;

    private final GoogleAuthApiClient googleAuthApiCaller;

    private final UserService userService;

    @Override
    public Long signUp(SignUpDto request) {
        GoogleProfileInfoResponse response = googleAuthApiCaller.getProfileInfo((HttpHeaderUtils.withBearerToken(request.getToken())));
        return userService.registerUser(request.toCreateUserDto(response.getId()));
    }

    @Override
    public Long login(LoginDto request) {
        GoogleProfileInfoResponse response = googleAuthApiCaller.getProfileInfo((HttpHeaderUtils.withBearerToken(request.getToken())));
        return UserServiceUtils.findUserBySocialIdAndSocialType(userRepository, response.getId(), socialType).getId();
    }
}
