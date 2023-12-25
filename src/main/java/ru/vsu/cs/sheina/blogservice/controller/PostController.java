package ru.vsu.cs.sheina.blogservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.sheina.blogservice.dto.request.PostCreateDTO;
import ru.vsu.cs.sheina.blogservice.dto.request.PostLikeDTO;
import ru.vsu.cs.sheina.blogservice.dto.request.PostUpdateDTO;
import ru.vsu.cs.sheina.blogservice.dto.response.PostDTO;
import ru.vsu.cs.sheina.blogservice.service.PostService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getAllPosts(@PathVariable UUID id,
                                         @RequestHeader("Authorization") String token) {
        List<PostDTO> posts = postService.getAllUsersPosts(id, token);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping("/post/create")
    public ResponseEntity<?> createPost(@RequestBody PostCreateDTO postCreateDTO,
                                        @RequestHeader("Authorization") String token) {
        postService.createPost(postCreateDTO, token);
        return ResponseEntity.ok("Post created successfully");
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<?> getPost(@PathVariable Integer id,
                                     @RequestHeader("Authorization") String token) {
        PostDTO postDTO = postService.getPost(id, token);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @PutMapping("/post")
    public ResponseEntity<?> editPost(@RequestBody PostUpdateDTO postUpdateDTO,
                                      @RequestHeader("Authorization") String token) {
        postService.editPost(postUpdateDTO, token);
        return ResponseEntity.ok("Post updated successfully");
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Integer id,
                                        @RequestHeader("Authorization") String token) {
        postService.deletePost(id, token);
        return ResponseEntity.ok("Post deleted successfully");
    }

    @PutMapping("/post/like")
    public ResponseEntity<?> likePost(@RequestBody PostLikeDTO postLikeDTO,
                                      @RequestHeader("Authorization") String token) {
        postService.likePost(postLikeDTO, token);
        return ResponseEntity.ok("Action done successfully");
    }
}
