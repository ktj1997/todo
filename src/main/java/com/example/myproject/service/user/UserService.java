package com.example.myproject.service.user;

import com.example.myproject.exception.UserNotExistException;
import com.example.myproject.model.dto.resonse.user.UserInfoDto;
import com.example.myproject.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserInfoDto getUserInfo() {
        return new UserInfoDto(userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(UserNotExistException::new));
    }
}
