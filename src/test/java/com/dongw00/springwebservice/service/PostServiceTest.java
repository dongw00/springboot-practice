package com.dongw00.springwebservice.service;

import com.dongw00.springwebservice.Service.PostsService;
import com.dongw00.springwebservice.domain.posts.Posts;
import com.dongw00.springwebservice.domain.posts.PostsRepository;
import com.dongw00.springwebservice.dto.posts.PostsSaveRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {
    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void Dto가_posts테이블에_저장() {
        // given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .author("albadw0428@gmail.com")
                .content("테스트")
                .title("테스트 타이틀")
                .build();

        // when
        postsService.save(dto);

        // then
        Posts posts = postsRepository.findAll().get(0);
        Assertions.assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
        Assertions.assertThat(posts.getContent()).isEqualTo(dto.getContent());
        Assertions.assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
    }
}
