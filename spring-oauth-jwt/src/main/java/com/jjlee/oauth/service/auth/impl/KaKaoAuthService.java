package com.jjlee.oauth.service.auth.impl;

import com.jjlee.oauth.common.util.HttpHeaderUtils;
import com.jjlee.oauth.domain.user.UserRepository;
import com.jjlee.oauth.domain.user.UserSocialType;
import com.jjlee.oauth.external.client.kakao.KaKaoAuthApiClient;
import com.jjlee.oauth.external.client.kakao.dto.response.KaKaoProfileResponse;
import com.jjlee.oauth.service.auth.AuthService;
import com.jjlee.oauth.service.auth.dto.request.LoginDto;
import com.jjlee.oauth.service.auth.dto.request.SignUpDto;
import com.jjlee.oauth.service.user.UserService;
import com.jjlee.oauth.service.user.UserServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KaKaoAuthService implements AuthService {

    private static final UserSocialType socialType = UserSocialType.KAKAO;
    private final UserRepository userRepository;

    private final KaKaoAuthApiClient kakaoAuthApiCaller;

    private final UserService userService;
    @Override
    public Long signUp(SignUpDto request) {
        KaKaoProfileResponse response = kakaoAuthApiCaller.getProfileInfo(HttpHeaderUtils.withBearerToken(request.getToken()));
        return userService.registerUser(request.toCreateUserDto(response.getId()));
    }

    @Override
    public Long login(LoginDto request) {
        KaKaoProfileResponse response = kakaoAuthApiCaller.getProfileInfo(HttpHeaderUtils.withBearerToken(request.getToken()));
        return UserServiceUtils.findUserBySocialIdAndSocialType(userRepository, response.getId(), socialType).getId();
    }
}
