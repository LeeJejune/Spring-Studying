package com.jjlee.oauth.domain.user.repository;


import com.jjlee.oauth.domain.user.User;
import com.jjlee.oauth.domain.user.UserSocialType;

public interface UserRepositoryCustom {
    boolean existsByName(String name);
    boolean existsBySocialIdAndSocialType(String socialId, UserSocialType socialType);
    User findUserById(Long id);
    User findUserBySocialIdAndSocialType(String socialId, UserSocialType socialType);
}
