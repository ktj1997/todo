package com.example.myproject.service.comment;

import com.example.myproject.exception.UserNotExistException;
import com.example.myproject.model.dto.request.comment.CommentRequestDto;
import com.example.myproject.model.dto.request.user.SignUpRequestDto;
import com.example.myproject.model.entity.comment.Comment;
import com.example.myproject.model.entity.post.Post;
import com.example.myproject.model.entity.post.PostType;
import com.example.myproject.model.entity.user.User;
import com.example.myproject.repository.comment.CommentRepository;
import com.example.myproject.repository.post.PostRepository;
import com.example.myproject.repository.user.UserRepository;
import com.example.myproject.service.auth.AuthService;
import com.example.myproject.service.post.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.transaction.Transactional;
import java.io.IOException;

@SpringBootTest
@Transactional
class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Autowired
    PostService postService;

    @Autowired
    AuthService authService;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    Post post = Post.builder()
            .title("TestPostTitle")
            .content("TestPostContent")
            .type(PostType.PUBLIC)
            .build();
    Comment comment = Comment.builder()
            .content("TestCommentContent")
            .build();

    @BeforeEach
    void createCommentBeforeEach() throws IOException {
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto("Test1234", "password123!", "test@gmail.com");
        authService.signUp(signUpRequestDto);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("Test1234", "password123!"));
        User user = userRepository.findByUserName("Test1234").orElseThrow(UserNotExistException::new);

        post.setUser(user);
        comment.setUser(user);

        postRepository.save(post);
        commentRepository.save(comment);
    }

    @Test
    void getComment() {
        Assertions.assertNotNull(comment.getId());

    }

    @Test
    void createReComment() {
        CommentRequestDto commentRequestDto = new CommentRequestDto("TestComment");
        commentService.createReComment(post.getId(), comment.getId(), commentRequestDto);
        Assertions.assertNotNull(commentRepository.findById(comment.getId()));

    }

    @Test
    void updateComment() {
        CommentRequestDto commentRequestDto = new CommentRequestDto("UpdateComment");
        commentService.updateComment(comment.getId(), commentRequestDto);

        Assertions.assertSame(commentRequestDto.getContent(), commentRepository.findById((long) 1).get().getContent());

    }

    @Test
    void deleteComment() {
        commentService.deleteComment(post.getId(), comment.getId());
        Assertions.assertEquals(true,commentRepository.findById(comment.getId()).get().getDeleted());
    }
}