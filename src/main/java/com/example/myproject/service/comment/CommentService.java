package com.example.myproject.service.comment;

import com.example.myproject.exception.AccessDeniedException;
import com.example.myproject.exception.CommentNotExistException;
import com.example.myproject.exception.MemoNotExistException;
import com.example.myproject.exception.UserNotExistException;
import com.example.myproject.model.dto.request.comment.CommentRequestDto;
import com.example.myproject.model.dto.resonse.comment.CommentResponseDto;
import com.example.myproject.model.entity.comment.Comment;
import com.example.myproject.model.entity.memo.Memo;
import com.example.myproject.model.entity.memo.MemoPage;
import com.example.myproject.model.entity.user.User;
import com.example.myproject.repository.comment.CommentRepository;
import com.example.myproject.repository.memo.MemoRepository;
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
    private final MemoRepository memoRepository;
    private final UserRepository userRepository;


    @Transactional
    public List<CommentResponseDto> getComment(Long memoId, int page) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(MemoNotExistException::new);
        return commentRepository.findAllByMemoOrderByGroupNum(memo, new MemoPage(page)).stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void createComment(CommentRequestDto commentRequestDto) {
        Memo memo = memoRepository.findById(commentRequestDto.getMemoId()).orElseThrow(MemoNotExistException::new);
        User user = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(UserNotExistException::new);
        Comment comment = Comment.builder()
                .memo(memo)
                .content(commentRequestDto.getContent())
                .user(user)
                .build();

        commentRepository.save(comment);

        comment.setGroupNum(comment.getId());
        memo.setCommentNum(memo.getCommentNum() + 1);
    }

    @Transactional
    public void createReComment(Long memoId, Long commentId, CommentRequestDto commentRequestDto) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(MemoNotExistException::new);
        User user = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(UserNotExistException::new);
        Comment parentComment = commentRepository.findById(commentId).orElseThrow(CommentNotExistException::new);
        commentRepository.save(Comment.builder()
                .memo(memo)
                .content(commentRequestDto.getContent())
                .groupNum(parentComment.getId())
                .user(user)
                .parentComment(parentComment)
                .build());
        memo.setCommentNum(memo.getCommentNum() + 1);
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
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotExistException::new);
        User user = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(UserNotExistException::new);

        if (comment.getUser() != user)
            throw new AccessDeniedException();
        comment.deleteComment();
    }
}
