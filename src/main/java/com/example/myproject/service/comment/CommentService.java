package com.example.myproject.service.comment;

import com.example.myproject.exception.AccessDeniedException;
import com.example.myproject.exception.CommentNotExistException;
import com.example.myproject.exception.PostNotExistException;
import com.example.myproject.exception.UserNotExistException;
import com.example.myproject.model.dto.request.comment.CommentRequestDto;
import com.example.myproject.model.dto.resonse.comment.CommentResponseDto;
import com.example.myproject.model.entity.comment.Comment;
import com.example.myproject.model.entity.post.Post;
import com.example.myproject.model.entity.post.PostPage;
import com.example.myproject.model.entity.user.User;
import com.example.myproject.repository.comment.CommentRepository;
import com.example.myproject.repository.post.PostRepository;
import com.example.myproject.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    @Transactional
    public List<CommentResponseDto> getComment(Long memoId, int page) {
        Post post = postRepository.findById(memoId).orElseThrow(PostNotExistException::new);
        return commentRepository.findAllByPostOrderByGroupNum(post, new PostPage(page)).stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void createComment(Long postId, CommentRequestDto commentRequestDto) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotExistException::new);
        User user = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(UserNotExistException::new);
        Comment comment = Comment.builder()
                .post(post)
                .content(commentRequestDto.getContent())
                .user(user)
                .build();

        commentRepository.save(comment);

        comment.setGroupNum(comment.getId());
        post.setCommentNum(post.getCommentNum() + 1);
    }

    @Transactional
    public void createReComment(Long postId, Long commentId, CommentRequestDto commentRequestDto) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotExistException::new);
        User user = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(UserNotExistException::new);
        Comment parentComment = commentRepository.findById(commentId).orElseThrow(CommentNotExistException::new);
        commentRepository.save(Comment.builder()
                .post(post)
                .content(commentRequestDto.getContent())
                .groupNum(parentComment.getId())
                .user(user)
                .parentComment(parentComment)
                .build());
        post.setCommentNum(post.getCommentNum() + 1);
        parentComment.setReCommentNum(parentComment.getReCommentNum() + 1);
    }

    @Transactional
    public void updateComment(Long commentId, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotExistException::new);
        User user = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(UserNotExistException::new);

        if (comment.getUser() != user)
            throw new AccessDeniedException();

        comment.updateComment(commentRequestDto);
    }

    @Transactional
    public void deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotExistException::new);
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotExistException::new);
        User user = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(UserNotExistException::new);

        if (comment.getUser() != user)
            throw new AccessDeniedException();
        comment.deleteComment();
        post.setCommentNum(post.getCommentNum() - 1);
    }
}
