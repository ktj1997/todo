package com.example.myproject.service.post;

import com.example.myproject.exception.AccessDeniedException;
import com.example.myproject.exception.PostNotExistException;
import com.example.myproject.exception.UserNotExistException;
import com.example.myproject.model.dto.request.post.PostRequestDto;
import com.example.myproject.model.dto.resonse.post.PostDetailDto;
import com.example.myproject.model.dto.resonse.post.PostSummaryDto;
import com.example.myproject.model.entity.post.Image;
import com.example.myproject.model.entity.post.Post;
import com.example.myproject.model.entity.post.PostPage;
import com.example.myproject.model.entity.user.User;
import com.example.myproject.repository.post.ImageRepository;
import com.example.myproject.repository.post.PostRepository;
import com.example.myproject.repository.user.UserRepository;
import com.example.myproject.service.post.function.StoreImgFunction;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final StoreImgFunction storeImgFunction;

    @Transactional
    public PostDetailDto getPost(long postId) {
        return new PostDetailDto(postRepository.findById(postId)
                .orElseThrow(PostNotExistException::new));
    }

    @Transactional
    public void createPost(MultipartFile[] files, PostRequestDto postRequestDto) throws IOException {
        User user = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(UserNotExistException::new);
        List<Image> imgURL = null;
        Post post = Post.builder()
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .type(postRequestDto.getType())
                .user(user).build();
        postRepository.save(post);

        if (files != null) {
            imgURL = storeImgFunction.StoreImgToS3(files).stream().map(it -> new Image(it, post)).collect(Collectors.toList());
            imgURL.forEach(imageRepository::save);
            post.setImageURL(imgURL);
        }
    }

    @Transactional
    public void updatePost(Long memoId, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(memoId).orElseThrow(PostNotExistException::new);
        User user = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(UserNotExistException::new);

        if (post.getUser() != user)
            throw new AccessDeniedException();

        post.updateMemo(postRequestDto);

    }

    @Transactional
    public void deletePost(Long memoId) {
        Post post = postRepository.findById(memoId).orElseThrow(PostNotExistException::new);
        User user = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(UserNotExistException::new);
        if (post.getUser() != user)
            throw new AccessDeniedException();

        postRepository.delete(post);
    }

    @Transactional
    public List<PostSummaryDto> getAllPostSummary(int page) {
        return postRepository.findAllBy(new PostPage(page)).stream().map(it -> new PostSummaryDto(it)).collect(Collectors.toList());
    }
}
