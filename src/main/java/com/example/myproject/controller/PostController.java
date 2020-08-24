package com.example.myproject.controller;

import com.example.myproject.model.dto.request.post.PostRequestDto;
import com.example.myproject.model.dto.resonse.post.PostDetailDto;
import com.example.myproject.model.dto.resonse.post.PostSummaryDto;
import com.example.myproject.service.post.PostService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memo")
public class PostController {

    private final PostService postService;

    @ApiOperation("게시글 리스트 보기")
    @GetMapping("/")
    public List<PostSummaryDto> getPosts(@RequestParam int page) {
        return postService.getAllPostSummary(page);
    }

    @ApiOperation("게시글 상세보기 ")
    @GetMapping("/{postId}")
    public PostDetailDto getPost(@PathVariable long postId) {
        return postService.getPost(postId);
    }

    @ApiOperation("게시글 작성하기")
    @PostMapping(value = "/", consumes = (MediaType.ALL_VALUE))
    public void createPost(@RequestPart MultipartFile[] files, @RequestPart PostRequestDto postRequestCreationDto) throws IOException {
        postService.createPost(files, postRequestCreationDto);
    }

    @ApiOperation("게시글 수정하기")
    @PutMapping("/{postId}")
    public void updatePost(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto) {
        postService.updatePost(postId, postRequestDto);
    }

    @ApiOperation("게시글 삭제하기")
    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }


}
