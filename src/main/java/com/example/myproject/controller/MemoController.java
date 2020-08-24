package com.example.myproject.controller;

import com.example.myproject.model.dto.request.memo.MemoRequestDto;
import com.example.myproject.model.dto.resonse.memo.MemoDetailDto;
import com.example.myproject.model.dto.resonse.memo.MemoSummaryDto;
import com.example.myproject.service.memo.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memo")
public class MemoController {

    private final MemoService memoService;

    /*
        모든 메모 리스트로 보기
     */
    @GetMapping("/")
    public List<MemoSummaryDto> getMemos(@RequestParam int page) {
        return memoService.getAllMemoSummary(page);
    }

    /*
        메모 상세보기
     */
    @GetMapping("/{memoId}")
    public MemoDetailDto getMemo(@PathVariable long memoId) {
        return memoService.getMemo(memoId);
    }

    /*
        메모 작성하기
     */
    @PostMapping(value = "/",consumes = (MediaType.ALL_VALUE))
    public void createMemo(@RequestPart MultipartFile[] files, @RequestPart MemoRequestDto memoRequestCreationDto) throws IOException {
        memoService.createMemo(files,memoRequestCreationDto);
    }
    /*
        메모수정
     */
    @PutMapping("/{memoId}")
    public void updateMemo(@PathVariable Long memoId,@RequestBody MemoRequestDto memoRequestDto)
    {
        memoService.updateMemo(memoId,memoRequestDto);
    }
    @DeleteMapping("/{memoId}")
    public void deleteMemo(@PathVariable Long memoId)
    {
        memoService.deleteMemo(memoId);
    }


}
