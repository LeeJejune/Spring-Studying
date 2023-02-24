package com.jpa.projection.domain.user;

import com.jpa.projection.domain.post.Post;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nickname;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Post> members = new ArrayList<>();

    private User(final String nickname) {
        this.nickname = nickname;
    }
    public static User newInstance(final String nickname){
        return new User(nickname);
    }
}
