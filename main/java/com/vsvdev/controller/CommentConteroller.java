package com.vsvdev.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsvdev.dto.CommentDTO;
import com.vsvdev.entity.Comment;
import com.vsvdev.facade.CommentFacade;
import com.vsvdev.payload.MessageResponse;
import com.vsvdev.services.CommentService;
import com.vsvdev.validation.ResponseErrorValidation;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin
public class CommentConteroller {

	private final CommentService service;
	private final CommentFacade facade;
	private final ResponseErrorValidation responseError;

	@Autowired
	public CommentConteroller(CommentService service, CommentFacade facade, ResponseErrorValidation responseError) {
		this.service = service;
		this.facade = facade;
		this.responseError = responseError;
	}

	@PostMapping("/{postId}/create")
	public ResponseEntity<Object> createComment(@Valid @RequestBody CommentDTO commentDTO,
			@PathVariable("postId") String postId, BindingResult result, Principal principal) {
		ResponseEntity<Object> errors = responseError.mapValidationService(result);
		if (!ObjectUtils.isEmpty(errors)) {
			return errors;
		}
		Comment comment = service.saveComment(Long.parseLong(postId), commentDTO, principal);
		CommentDTO createdComment = facade.commentToCommentDTO(comment);
		return new ResponseEntity<>(createdComment, HttpStatus.OK);
	}

	@GetMapping("/{postId}/all")
	public ResponseEntity<List<CommentDTO>> getAllCommentsToPost(@PathVariable("postId") String postId) {
		List<CommentDTO> commentDTOList = service.getAllCommentsForPost(Long.parseLong(postId)).stream()
				.map(facade::commentToCommentDTO).collect(Collectors.toList());
		return new ResponseEntity<>(commentDTOList, HttpStatus.OK);

	}

	@DeleteMapping("/{commentId}/delete")
	public ResponseEntity<MessageResponse> deleteComment(@PathVariable("commentId") String commentId) {
		service.deleteComment(Long.parseLong(commentId));
		return new ResponseEntity<>(new MessageResponse("Comment deleted"), HttpStatus.OK);
	}
}
