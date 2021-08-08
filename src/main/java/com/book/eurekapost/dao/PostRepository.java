package com.book.eurekapost.dao;


import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    PostEntity findById(String postId);
    Integer deleteById(String postId);
}
