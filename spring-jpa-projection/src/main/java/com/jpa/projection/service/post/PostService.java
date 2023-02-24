package com.jpa.projection.service.post;

import com.jpa.projection.domain.post.Post;
import com.jpa.projection.domain.post.PostRepository;
import com.jpa.projection.service.post.request.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;

    public void createInitPost(PostRequestDto request){
        final Post post = Post.newInstance(request.getTitle(), request.getDescription());
        postRepository.save(post);
    }
}
