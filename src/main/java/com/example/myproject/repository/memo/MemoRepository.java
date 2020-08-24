package com.example.myproject.repository.memo;


import com.example.myproject.model.entity.memo.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemoRepository
        extends JpaRepository<Memo, Long> {

    Optional<Memo> findMemoDetailById(long id);

    List<Memo> findAllBy(Pageable pageable);
}
