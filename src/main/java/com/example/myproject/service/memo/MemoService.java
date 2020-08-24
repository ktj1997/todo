package com.example.myproject.service.memo;

import com.example.myproject.exception.AccessDeniedException;
import com.example.myproject.exception.MemoNotExistException;
import com.example.myproject.exception.UserNotExistException;
import com.example.myproject.model.dto.request.memo.MemoRequestDto;
import com.example.myproject.model.dto.resonse.memo.MemoDetailDto;
import com.example.myproject.model.dto.resonse.memo.MemoSummaryDto;
import com.example.myproject.model.entity.memo.Image;
import com.example.myproject.model.entity.memo.Memo;
import com.example.myproject.model.entity.memo.MemoPage;
import com.example.myproject.model.entity.user.User;
import com.example.myproject.repository.memo.ImageRepository;
import com.example.myproject.repository.memo.MemoRepository;
import com.example.myproject.repository.user.UserRepository;
import com.example.myproject.service.memo.function.StoreImgFunction;
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
public class MemoService {

    private final MemoRepository memoRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final StoreImgFunction storeImgFunction;

    @Transactional
    public MemoDetailDto getMemo(long memoId) {
        return new MemoDetailDto(memoRepository.findMemoDetailById(memoId)
                .orElseThrow(MemoNotExistException::new));
    }

    @Transactional
    public void createMemo(MultipartFile[] files, MemoRequestDto memoRequestDto) throws IOException {
        User user = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(UserNotExistException::new);
        List<Image> imgURL = null;
        Memo memo = Memo.builder()
                .title(memoRequestDto.getTitle())
                .content(memoRequestDto.getContent())
                .type(memoRequestDto.getType())
                .user(user).build();
        memoRepository.save(memo);

        if (files != null) {
            imgURL = storeImgFunction.StoreImgToS3(files).stream().map(it -> new Image(it, memo)).collect(Collectors.toList());
            imgURL.forEach(imageRepository::save);
            memo.setImageURL(imgURL);
        }
    }

    @Transactional
    public void updateMemo(Long memoId, MemoRequestDto memoRequestDto) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(MemoNotExistException::new);
        User user = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(UserNotExistException::new);

        if (memo.getUser() != user)
            throw new AccessDeniedException();

        memo.updateMemo(memoRequestDto);

    }

    @Transactional
    public void deleteMemo(Long memoId) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(MemoNotExistException::new);
        User user = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(UserNotExistException::new);
        if (memo.getUser() != user)
            throw new AccessDeniedException();

        memoRepository.delete(memo);
    }

    @Transactional
    public List<MemoSummaryDto> getAllMemoSummary(int page) {
        return memoRepository.findAllBy(new MemoPage(page)).stream().map(it -> new MemoSummaryDto(it)).collect(Collectors.toList());
    }
}
