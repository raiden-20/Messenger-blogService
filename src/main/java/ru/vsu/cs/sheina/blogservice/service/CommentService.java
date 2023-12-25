package ru.vsu.cs.sheina.blogservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.cs.sheina.blogservice.dto.request.CommentCreateDTO;
import ru.vsu.cs.sheina.blogservice.dto.request.CommentLikeDTO;
import ru.vsu.cs.sheina.blogservice.dto.request.CommentUpdateDTO;
import ru.vsu.cs.sheina.blogservice.dto.response.CommentDTO;
import ru.vsu.cs.sheina.blogservice.entity.CommentEntity;
import ru.vsu.cs.sheina.blogservice.entity.CommentLikeEntity;
import ru.vsu.cs.sheina.blogservice.entity.PostEntity;
import ru.vsu.cs.sheina.blogservice.exception.AccessException;
import ru.vsu.cs.sheina.blogservice.exception.CommentDoesntExistException;
import ru.vsu.cs.sheina.blogservice.exception.PostDoesntExistException;
import ru.vsu.cs.sheina.blogservice.exception.TextTooBigException;
import ru.vsu.cs.sheina.blogservice.repository.CommentLikeRepository;
import ru.vsu.cs.sheina.blogservice.repository.CommentRepository;
import ru.vsu.cs.sheina.blogservice.repository.PostRepository;
import ru.vsu.cs.sheina.blogservice.util.JwtTokenUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final JwtTokenUtil jwtTokenUtil;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final Integer MAX_COMMENT_SIZE = 400;

    public void createComment(CommentCreateDTO commentCreateDTO, String token) {
        UUID userId = jwtTokenUtil.retrieveIdClaim(token);

        if(!postRepository.existsById(commentCreateDTO.getPostId())) {
            throw new PostDoesntExistException();
        }

        if (commentCreateDTO.getText().length() > MAX_COMMENT_SIZE) {
            throw new TextTooBigException();
        }

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setUserId(userId);
        commentEntity.setPostId(commentCreateDTO.getPostId());
        commentEntity.setBody(commentCreateDTO.getText());
        commentEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        commentEntity.setChanged(false);

        commentRepository.save(commentEntity);
    }

    public List<CommentDTO> getCommentsOnPost(Integer id, String token) {
        UUID currentId = jwtTokenUtil.retrieveIdClaim(token);

        if(!postRepository.existsById(id)) {
            throw new PostDoesntExistException();
        }

        List<CommentEntity> comments = commentRepository.findAllByPostId(id);
        List<CommentDTO> commentDtos = new ArrayList<>();

        for(CommentEntity comment: comments) {
            commentDtos.add(createCommentDto(comment.getId(), currentId));
        }

        return commentDtos;
    }

    private CommentDTO createCommentDto(Integer commentId, UUID userId) {
        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow(CommentDoesntExistException::new);
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentId(commentId);
        commentDTO.setTime(commentEntity.getCreatedAt());
        commentDTO.setText(commentEntity.getBody());
        commentDTO.setUserId(commentEntity.getUserId());

        Integer likeCount = commentLikeRepository.countByCommentId(commentId);
        commentDTO.setLikeCount(likeCount);

        if (commentLikeRepository.existsByCommentIdAndUserId(commentId, userId)) {
            commentDTO.setIsLiked(true);
        } else {
            commentDTO.setIsLiked(false);
        }

        return commentDTO;
    }

    public void editComment(CommentUpdateDTO commentUpdateDTO, String token) {
        UUID userId = jwtTokenUtil.retrieveIdClaim(token);
        CommentEntity commentEntity = commentRepository.findById(commentUpdateDTO.getCommentId()).orElseThrow(CommentDoesntExistException::new);

        if (!commentEntity.getUserId().equals(userId)) {
            throw new AccessException();
        }

        commentEntity.setBody(commentUpdateDTO.getText());
        commentEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        commentEntity.setChanged(true);

        commentRepository.save(commentEntity);
    }

    public void likeComment(CommentLikeDTO commentLikeDTO, String token) {
        UUID userId = jwtTokenUtil.retrieveIdClaim(token);
        CommentEntity commentEntity = commentRepository.findById(commentLikeDTO.getCommentId()).orElseThrow(CommentDoesntExistException::new);

        Optional<CommentLikeEntity> commentLikeEntity = commentLikeRepository.findByCommentIdAndUserId(commentEntity.getId(), userId);
        if (commentLikeEntity.isPresent()) {
            commentLikeRepository.delete(commentLikeEntity.get());
        } else {
            CommentLikeEntity newCommentLikeEntity = new CommentLikeEntity();
            newCommentLikeEntity.setUserId(userId);
            newCommentLikeEntity.setCommentId(commentEntity.getId());
            commentLikeRepository.save(newCommentLikeEntity);
        }
    }

    public void deleteComment(Integer id, String token) {
        UUID userId = jwtTokenUtil.retrieveIdClaim(token);
        CommentEntity commentEntity = commentRepository.findById(id).orElseThrow(CommentDoesntExistException::new);

        if (!commentEntity.getUserId().equals(userId)) {
            throw new AccessException();
        }

        if (commentLikeRepository.existsByCommentId(id)) {
            commentLikeRepository.deleteAllByCommentId(id);
        }

        commentRepository.delete(commentEntity);
    }
}
