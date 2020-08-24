package com.example.myproject.repository.post;


import com.example.myproject.model.entity.post.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository
        extends JpaRepository<Post, Long> {

    Optional<Post> findMemoDetailById(long id);

    List<Post> findAllBy(Pageable pageable);
}