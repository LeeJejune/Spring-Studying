package com.jjlee.oauth.config.interceptor;

import javax.servlet.http.HttpServletRequest;

import com.jjlee.oauth.config.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.jjlee.oauth.config.jwt.JwtHeader.AUTH;

@RequiredArgsConstructor
@Component
public class LoginCheckHandler {
    private final JwtService jwtService;

    Long getUserId(final HttpServletRequest request) {
        final String token = request.getHeader(AUTH);
        final String subject = jwtService.getSubject(token);
        return convertToUserId(subject);
    }

    private long convertToUserId(final String subject) {
            return Long.parseLong(subject);
    }

}
