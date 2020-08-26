package com.example.myproject.service.post;

import com.example.myproject.exception.PostNotExistException;
import com.example.myproject.exception.UserNotExistException;
import com.example.myproject.model.dto.request.post.PostRequestDto;
import com.example.myproject.model.dto.request.user.SignUpRequestDto;
import com.example.myproject.model.entity.post.Post;
import com.example.myproject.model.entity.post.PostType;
import com.example.myproject.model.entity.user.User;
import com.example.myproject.repository.post.PostRepository;
import com.example.myproject.repository.user.UserRepository;
import com.example.myproject.service.auth.AuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    AuthService authService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    Post post = Post.builder()
            .title("TestPostTitle")
            .content("TestPostContent")
            .type(PostType.PUBLIC)
            .build();

    @BeforeEach
    void postCreateBeforeEach() {
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto("Test1234", "password123!", "test@gmail.com");
        authService.signUp(signUpRequestDto);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("Test1234", "password123!"));
        User user = userRepository.findByUserName("Test1234").orElseThrow(UserNotExistException::new);

        post.setUser(user);
        postRepository.save(post);
    }

    @Test
    void getPost() {
        Assertions.assertNotNull(postRepository.findById(post.getId()));
    }

    @Test
    void updatePost() {
        PostRequestDto postRequestDto = new PostRequestDto("UpdateTitle", "UpdateContent", PostType.PUBLIC);
        postService.updatePost(post.getId(), postRequestDto);

        Assertions.assertEquals("UpdateTitle", postRepository.findById(post.getId()).get().getTitle());

    }

    @Test
    void deletePost() {
        postService.deletePost(post.getId());
        Assertions.assertThrows(PostNotExistException.class,() -> postRepository.findById(post.getId()).orElseThrow(PostNotExistException::new));
    }

    @Test
    void getAllPostSummary() {
        Assertions.assertEquals(1, postService.getAllPostSummary(0).size());
    }
}