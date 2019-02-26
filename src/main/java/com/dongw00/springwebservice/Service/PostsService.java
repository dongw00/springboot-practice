package com.dongw00.springwebservice.Service;

import com.dongw00.springwebservice.domain.posts.Posts;
import com.dongw00.springwebservice.domain.posts.PostsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class PostsService {
    private PostsRepository postsRepository;

    @Transactional
    public void save(Posts entity) {
        postsRepository.save(entity);
    }
}
