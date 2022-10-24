package com.jjlee.oauth.controller.auth;

import com.jjlee.oauth.config.jwt.JwtService;
import com.jjlee.oauth.controller.auth.dto.request.LoginRequestDto;
import com.jjlee.oauth.controller.auth.dto.request.SignUpRequestDto;
import com.jjlee.oauth.controller.auth.dto.response.LoginResponse;
import com.jjlee.oauth.domain.user.User;
import com.jjlee.oauth.domain.user.UserRepository;
import com.jjlee.oauth.service.auth.AuthService;
import com.jjlee.oauth.service.auth.AuthServiceProvider;
import com.jjlee.oauth.service.user.UserServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class AuthController {
    private final UserRepository userRepository;
    private final AuthServiceProvider authServiceProvider;
    private final JwtService jwtService;

    @PostMapping("/v1/signup")
    public LoginResponse signUp(@Valid @RequestBody final SignUpRequestDto request) {
        final AuthService authService = authServiceProvider.getAuthService(request.getSocialType());
        final Long userId = authService.signUp(request.toServiceDto());
        final String token = jwtService.issuedToken(String.valueOf(userId), "USER", 60 * 60 * 24 * 30L);
        return LoginResponse.of(token, userId);
    }

    @PostMapping("/v1/login")
    public LoginResponse login(@Valid @RequestBody final LoginRequestDto request) {
        final AuthService authService = authServiceProvider.getAuthService(request.getSocialType());
        final Long userId = authService.login(request.toServiceDto());
        final String token = jwtService.issuedToken(String.valueOf(userId), "USER", 60 * 60 * 24 * 30L);

        User findUser = UserServiceUtils.findUserById(userRepository, userId);
        return LoginResponse.of(token, userId);
    }
}
