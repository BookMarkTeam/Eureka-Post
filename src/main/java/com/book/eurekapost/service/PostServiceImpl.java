package com.book.eurekapost.service;

import com.book.eurekapost.dao.PostEntity;
import com.book.eurekapost.dao.PostRepository;
import com.book.eurekapost.dto.PostDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;


@Service
public class PostServiceImpl implements PostService{
    PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        postDto.setCreatedAt(new Date(System.currentTimeMillis()));
        postDto.setHit(0);
        postDto.setLikeCount(0);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        PostEntity postEntity = mapper.map(postDto, PostEntity.class);

        postRepository.save(postEntity);

        return mapper.map(postEntity, PostDto.class);
    }

    @Override
    public PostDto getPostByPostId(String postId) {
        PostEntity postEntity = postRepository.findById(postId);

        // 없을 경우 예외처리

        return new ModelMapper().map(postEntity, PostDto.class);
    }

    @Override
    public Iterable<PostEntity> getPostByAll() {
        return postRepository.findAll();
    }

    @Override
    public PostDto updatePost(PostDto postDto) {
        postDto.setCreatedAt(new Date(System.currentTimeMillis()));

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        PostEntity postEntity = mapper.map(postDto, PostEntity.class);

        postRepository.save(postEntity);

        return mapper.map(postEntity, PostDto.class);
    }

    @Override
    public Integer deletePostById(String postId) {
        return postRepository.deleteById(postId);
    }

    @Override
    public void deletePosts() {
        postRepository.deleteAll();
    }
}
