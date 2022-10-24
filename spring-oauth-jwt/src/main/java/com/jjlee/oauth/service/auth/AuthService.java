package com.jjlee.oauth.service.auth;

import com.jjlee.oauth.service.auth.dto.request.LoginDto;
import com.jjlee.oauth.service.auth.dto.request.SignUpDto;

public interface AuthService {
    Long signUp(SignUpDto request);
    Long login(LoginDto loginDto);
}