package com.book.eurekapost.service;

import com.book.eurekapost.dao.PostEntity;
import com.book.eurekapost.dto.PostDto;

public interface PostService{
    PostDto createPost(PostDto postDto);
    PostDto getPostByPostId(String postId);
    Iterable<PostEntity> getPostByAll();
    PostDto updatePost(PostDto postDto);
    Integer deletePostById(String postId);
    void deletePosts();
}
