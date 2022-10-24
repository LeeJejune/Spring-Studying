package com.jjlee.oauth.service.user;
import com.jjlee.oauth.common.exception.ConflictException;
import com.jjlee.oauth.common.exception.ErrorCode;
import com.jjlee.oauth.common.exception.NotFoundException;
import com.jjlee.oauth.domain.user.User;
import com.jjlee.oauth.domain.user.UserRepository;
import com.jjlee.oauth.domain.user.UserSocialType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

import static com.jjlee.oauth.common.exception.ErrorCode.CONFLICT_NICKNAME_EXCEPTION;
import static com.jjlee.oauth.common.exception.ErrorCode.NOT_FOUND_USER_EXCEPTION;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserServiceUtils {
    static void validateNotExistsUserName(UserRepository userRepository, String name) {
        if (userRepository.existsByName(name)) {
            throw new ConflictException(String.format("이미 존재하는 닉네임 (%s) 입니다", name), CONFLICT_NICKNAME_EXCEPTION);
        }
    }

    static void validateNotExistsUser(UserRepository userRepository, String socialId, UserSocialType socialType) {
        if (userRepository.existsBySocialIdAndSocialType(socialId, socialType)) {
            throw new ConflictException(String.format("이미 존재하는 유저 (%s - %s) 입니다", socialId, socialType), ErrorCode.CONFLICT_USER_EXCEPTION);
        }
    }
    @NotNull
    public static User findUserBySocialIdAndSocialType(UserRepository userRepository, String socialId, UserSocialType socialType) {
        User user = userRepository.findUserBySocialIdAndSocialType(socialId, socialType);
        if (user == null) {
            throw new NotFoundException(String.format("존재하지 않는 유저 (%s - %s) 입니다", socialId, socialType), NOT_FOUND_USER_EXCEPTION);
        }
        return user;
    }
    @NotNull
    public static User findUserById(UserRepository userRepository, Long userId) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new NotFoundException(String.format("존재하지 않는 유저 (%s) 입니다", userId), NOT_FOUND_USER_EXCEPTION);
        }
        return user;
    }
}