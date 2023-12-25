package ru.vsu.cs.sheina.blogservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.sheina.blogservice.dto.request.CommentCreateDTO;
import ru.vsu.cs.sheina.blogservice.dto.request.CommentLikeDTO;
import ru.vsu.cs.sheina.blogservice.dto.request.CommentUpdateDTO;
import ru.vsu.cs.sheina.blogservice.dto.response.CommentDTO;
import ru.vsu.cs.sheina.blogservice.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/blog/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create")
    @CrossOrigin
    public ResponseEntity<?> createComment(@RequestBody CommentCreateDTO commentCreateDTO,
                                           @RequestHeader("Authorization") String token) {
        commentService.createComment(commentCreateDTO, token);
        return ResponseEntity.ok("Comment created successfully");
    }

    @PutMapping()
    @CrossOrigin
    public ResponseEntity<?> editComment(@RequestBody CommentUpdateDTO commentUpdateDTO,
                                           @RequestHeader("Authorization") String token) {
        commentService.editComment(commentUpdateDTO, token);
        return ResponseEntity.ok("Comment updated successfully");
    }

    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<?> getCommentsOnPost(@PathVariable Integer id,
                                               @RequestHeader("Authorization") String token) {
        List<CommentDTO> comments = commentService.getCommentsOnPost(id, token);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PutMapping("/like")
    @CrossOrigin
    public ResponseEntity<?> likeComment(@RequestBody CommentLikeDTO commentLikeDTO,
                                         @RequestHeader("Authorization") String token) {
        commentService.likeComment(commentLikeDTO, token);
        return ResponseEntity.ok("Action done successfully");
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<?> deleteComment(@PathVariable Integer id,
                                           @RequestHeader("Authorization") String token) {
        commentService.deleteComment(id, token);
        return ResponseEntity.ok("Comment deleted successfully");
    }
}
