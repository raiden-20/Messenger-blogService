package ru.vsu.cs.sheina.blogservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.sheina.blogservice.dto.response.PictureDTO;
import ru.vsu.cs.sheina.blogservice.service.PostService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/blog/photo")
@RequiredArgsConstructor
public class PictureController {

    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllPictureUrls(@PathVariable UUID id) {
        List<PictureDTO> pictureDtos = postService.getAllUserPictures(id);
        return new ResponseEntity<>(pictureDtos, HttpStatus.OK);
    }

    @GetMapping("/count/{id}")
    public ResponseEntity<?> getCountPictures(@PathVariable UUID id) {
        Integer count = postService.getCountPictures(id);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
