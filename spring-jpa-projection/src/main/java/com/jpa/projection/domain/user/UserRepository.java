package com.jpa.projection.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Long, User> {

    @Query(value = "select u.id, p.title, p.description from user u  " +
            "inner join plan p on p.user_id = u.id where u.id = :userId", nativeQuery = true)
    Optional<UserPost> findUserPost(@Param("userId") Long uesrId);
}
