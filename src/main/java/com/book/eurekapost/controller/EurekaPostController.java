package com.book.eurekapost.controller;

import com.book.eurekapost.dao.PostEntity;
import com.book.eurekapost.dto.PostDto;
import com.book.eurekapost.service.PostService;
import com.book.eurekapost.vo.RequestPost;
import com.book.eurekapost.vo.ResponsePost;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class EurekaPostController {
    private Environment env;
    private PostService postService;

    @Autowired
    public EurekaPostController(Environment env, PostService postService) {
        this.env = env;
        this.postService = postService;
    }

    @GetMapping("/test")
    public String test() {
        return "Post test";
    }

    @PostMapping("/posts")
    public ResponseEntity<ResponsePost> createPost(@RequestBody RequestPost post) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        PostDto postDto = mapper.map(post, PostDto.class);
        PostDto createdPost = postService.createPost(postDto);

        ResponsePost responsePost = mapper.map(createdPost, ResponsePost.class);
        log.debug(createdPost.toString());
        log.debug(responsePost.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(responsePost);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<ResponsePost>> getPosts() {
        Iterable<PostEntity> postList = postService.getPostByAll();

        List<ResponsePost> responsePosts = new ArrayList<>();
        postList.forEach(post -> {
            responsePosts.add(new ModelMapper().map(post, ResponsePost.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(responsePosts);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<ResponsePost> getPost(@PathVariable("postId") String postId) {
        PostDto postDto = postService.getPostByPostId(postId);

        ResponsePost returnPost = new ModelMapper().map(postDto, ResponsePost.class);

        return ResponseEntity.status(HttpStatus.OK).body(returnPost);
    }

    @PatchMapping("/posts")
    public ResponseEntity<ResponsePost> updatePost(@RequestBody RequestPost post) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        PostDto postDto = mapper.map(post, PostDto.class);
        postService.updatePost(postDto);

        ResponsePost responsePost = mapper.map(postDto, ResponsePost.class);

        return ResponseEntity.status(HttpStatus.OK).body(responsePost);
    }

   @DeleteMapping("/posts/{postId}")
   public ResponseEntity<Integer> deletePost(@PathVariable("postId") String postId) {
        Integer deleteCount = postService.deletePostById(postId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(deleteCount);
   }

   @DeleteMapping("/posts")
   public ResponseEntity deletePosts() {
       postService.deletePosts();

       return ResponseEntity.status(HttpStatus.NO_CONTENT).body("All Delete");
   }
}
