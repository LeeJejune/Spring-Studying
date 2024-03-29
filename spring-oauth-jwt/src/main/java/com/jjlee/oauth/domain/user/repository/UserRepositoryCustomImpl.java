package com.jjlee.oauth.domain.user.repository;

import com.jjlee.oauth.domain.user.User;
import com.jjlee.oauth.domain.user.UserSocialType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.persistence.LockModeType;

import static com.jjlee.oauth.domain.user.QUser.user;


@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public boolean existsByName(String name) {
        return queryFactory.selectOne()
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .setHint("javax.persistence.lock.timeout", 3000)
                .from(user)
                .where(user.nickname.eq(name))
                .fetchFirst() != null;
    }

    @Override
    public boolean existsBySocialIdAndSocialType(String socialId, UserSocialType socialType) {
        return queryFactory.selectOne()
                .from(user)
                .where(
                        user.socialInfo.socialId.eq(socialId),
                        user.socialInfo.socialType.eq(socialType)
                ).fetchFirst() != null;
    }

    @Override
    public User findUserById(Long userId) {
        return queryFactory.selectFrom(user)
                .where(
                        user.id.eq(userId)
                ).fetchOne();
    }

    @Override
    public User findUserBySocialIdAndSocialType(String socialId, UserSocialType socialType) {
        return queryFactory.selectFrom(user)
                .where(
                        user.socialInfo.socialId.eq(socialId),
                        user.socialInfo.socialType.eq(socialType)
                ).fetchOne();
    }
}
