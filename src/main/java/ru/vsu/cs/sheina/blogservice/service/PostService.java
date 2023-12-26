package ru.vsu.cs.sheina.blogservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.cs.sheina.blogservice.dto.rabbitmq.UrlDTO;
import ru.vsu.cs.sheina.blogservice.dto.request.PostCreateDTO;
import ru.vsu.cs.sheina.blogservice.dto.request.PostLikeDTO;
import ru.vsu.cs.sheina.blogservice.dto.request.PostUpdateDTO;
import ru.vsu.cs.sheina.blogservice.dto.response.PictureDTO;
import ru.vsu.cs.sheina.blogservice.dto.response.PostDTO;
import ru.vsu.cs.sheina.blogservice.entity.PostEntity;
import ru.vsu.cs.sheina.blogservice.entity.PostLikeEntity;
import ru.vsu.cs.sheina.blogservice.entity.PostPhotoEntity;
import ru.vsu.cs.sheina.blogservice.exception.AccessException;
import ru.vsu.cs.sheina.blogservice.exception.PostDoesntExistException;
import ru.vsu.cs.sheina.blogservice.exception.TextTooBigException;
import ru.vsu.cs.sheina.blogservice.exception.UserDoesntExistException;
import ru.vsu.cs.sheina.blogservice.repository.*;
import ru.vsu.cs.sheina.blogservice.util.JwtTokenUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserDataRepository userDataRepository;
    private final PostLikeRepository postLikeRepository;
    private final PostPhotoRepository postPhotoRepository;
    private final CommentRepository commentRepository;

    private final JwtTokenUtil jwtTokenUtil;
    private final Integer MAX_POST_SIZE = 1000;

    public void createPost(PostCreateDTO postCreateDTO, String token) {
        UUID id = jwtTokenUtil.retrieveIdClaim(token);

        if ((postCreateDTO.getText().length() > MAX_POST_SIZE)) {
            throw new TextTooBigException();
        }

        PostEntity postEntity = new PostEntity();
        postEntity.setUserId(id);
        postEntity.setBody(postCreateDTO.getText());
        postEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        postRepository.save(postEntity);
    }

    public List<PostDTO>  getAllUsersPosts(UUID id, String token) {
        UUID currentUser = jwtTokenUtil.retrieveIdClaim(token);

        if (!userDataRepository.existsById(id)) {
            throw new UserDoesntExistException();
        }

        List<PostEntity> posts = postRepository.findAllByUserId(id);
        List<PostDTO> postDtos = new ArrayList<>();

        for(PostEntity post: posts) {
            postDtos.add(createPostDto(post.getId(), currentUser));
        }

        return postDtos;
    }

    public PostDTO getPost(Integer id, String token) {
        UUID userId = jwtTokenUtil.retrieveIdClaim(token);

        if (! postRepository.existsById(id)) {
            throw new PostDoesntExistException();
        }

        return createPostDto(id, userId);
    }

    private PostDTO createPostDto(Integer id, UUID userId) {
        PostEntity postEntity = postRepository.findById(id).orElseThrow(PostDoesntExistException::new);
        PostDTO postDTO = new PostDTO();
        postDTO.setPostId(postEntity.getId());
        postDTO.setText(postEntity.getBody());
        postDTO.setTime(postEntity.getCreatedAt());

        Integer likeCount = postLikeRepository.countByPostId(id);
        Integer commentCount = commentRepository.countByPostId(id);

        postDTO.setLikeCount(likeCount);
        postDTO.setCommentCount(commentCount);

        if (postLikeRepository.existsByPostIdAndUserId(id, userId)) {
            postDTO.setIsLiked(true);
        } else {
            postDTO.setIsLiked(false);
        }

        List<PictureDTO> photoUrl = postPhotoRepository.findAllByPostId(id).stream()
                .map(ent -> new PictureDTO(id, ent.getId(), ent.getPhotoUrl()))
                .toList();
        postDTO.setPhotoUrl(photoUrl);

        return postDTO;
    }

    public void editPost(PostUpdateDTO postUpdateDTO, String token) {
        UUID userId = jwtTokenUtil.retrieveIdClaim(token);
        PostEntity postEntity = postRepository.findById(postUpdateDTO.getPostId()).orElseThrow(PostDoesntExistException::new);

        if (!postEntity.getUserId().equals(userId)) {
            throw new AccessException();
        }

        if ((postUpdateDTO.getText().length() > MAX_POST_SIZE)) {
            throw new TextTooBigException();
        }

        postEntity.setBody(postUpdateDTO.getText());
        postEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        postRepository.save(postEntity);
    }

    public void deletePost(Integer id, String token) {
        UUID userId = jwtTokenUtil.retrieveIdClaim(token);
        PostEntity postEntity = postRepository.findById(id).orElseThrow(PostDoesntExistException::new);

        if (!postEntity.getUserId().equals(userId)) {
            throw new AccessException();
        }

        if (postPhotoRepository.existsByPostId(id)) {
            postPhotoRepository.deleteAllByPostId(id);
        }
        if (postLikeRepository.existsByPostId(id)) {
            postLikeRepository.deleteAllByPostId(id);
        }

        postRepository.delete(postEntity);
    }

    public void likePost(PostLikeDTO postLikeDTO, String token) {
        UUID userId = jwtTokenUtil.retrieveIdClaim(token);

        if (!postRepository.existsById(postLikeDTO.getPostId())) {
            throw new PostDoesntExistException();
        }

        Optional<PostLikeEntity> postLikeEntity = postLikeRepository.findByPostIdAndUserId(postLikeDTO.getPostId(), userId);
        if (postLikeEntity.isPresent()) {
            postLikeRepository.delete(postLikeEntity.get());
        } else {
            PostLikeEntity newPostLikeEntity = new PostLikeEntity();
            newPostLikeEntity.setPostId(postLikeDTO.getPostId());
            newPostLikeEntity.setUserId(userId);
            postLikeRepository.save(newPostLikeEntity);
        }
    }

    public List<PictureDTO> getAllUserPictures(UUID id) {
        if (!userDataRepository.existsById(id)) {
            throw new UserDoesntExistException();
        }

        List<Integer> postIds = postRepository.findAllByUserId(id).stream()
                .map(PostEntity::getId)
                .toList();

        List<PictureDTO> pictureDtos = new ArrayList<>();

        for (Integer postId: postIds) {
            List<PostPhotoEntity> photoEntities = postPhotoRepository.findAllByPostId(postId);
            for (PostPhotoEntity entity: photoEntities) {
                pictureDtos.add(new PictureDTO(entity.getPostId(), entity.getId(), entity.getPhotoUrl()));
            }
        }

        return pictureDtos;
    }

    public Integer getCountPictures(UUID id) {
        if (!userDataRepository.existsById(id)) {
            throw new UserDoesntExistException();
        }

        Integer countPictures = 0;

        List<Integer> postIds = postRepository.findAllByUserId(id).stream()
                .map(PostEntity::getId)
                .toList();

        for (Integer postId: postIds) {
            List<PostPhotoEntity> photoEntities = postPhotoRepository.findAllByPostId(postId);
            countPictures += photoEntities.size();
        }

        return countPictures;
    }

    public void changeUrl(UrlDTO urlDTO) {
        if (urlDTO.getPhotoId() != 0) {
            PostPhotoEntity postPhotoEntity = new PostPhotoEntity();
            postPhotoEntity.setPostId(urlDTO.getPostId());
            postPhotoEntity.setPhotoUrl(urlDTO.getUrl());
            postPhotoRepository.save(postPhotoEntity);
        } else {
            postPhotoRepository.deleteById(urlDTO.getPhotoId());
        }
    }
}
