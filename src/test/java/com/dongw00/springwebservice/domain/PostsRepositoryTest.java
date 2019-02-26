package com.dongw00.springwebservice.domain;

import com.dongw00.springwebservice.domain.posts.Posts;
import com.dongw00.springwebservice.domain.posts.PostsRepository;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void loading_content() {
        // given
        postsRepository.save(Posts.builder()
            .title("테스트 게시글")
            .content("테스트 본문")
            .author("albadw0428@gmail.com")
            .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        Assert.assertThat(posts.getTitle(), CoreMatchers.is("테스트 게시글"));
        Assert.assertThat(posts.getContent(), CoreMatchers.is("테스트 본문"));
    }

    @Test
    public void BaseTimeEntity_등록() {
        // given
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
            .title("테스트 게시글")
            .content("테스트 본문")
            .author("albadw0428@gmail.com")
            .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        Assert.assertTrue(posts.getCreatedDate().isAfter(now));
        Assert.assertTrue(posts.getModifiedDate().isAfter(now));
    }
}
