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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsvdev.dto.PostDTO;

import com.vsvdev.entity.Post;
import com.vsvdev.facade.PostFacade;
import com.vsvdev.payload.MessageResponse;
import com.vsvdev.services.PostService;
import com.vsvdev.validation.ResponseErrorValidation;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin
public class PostController {

	private final PostFacade facade;
	private final PostService service;
	private final ResponseErrorValidation responseError;

	@Autowired
	public PostController(PostFacade facade, PostService service, ResponseErrorValidation responseError) {
		this.facade = facade;
		this.service = service;
		this.responseError = responseError;
	}

	@PostMapping("/create")
	public ResponseEntity<Object> createPost(@Valid @RequestBody PostDTO postDTO, BindingResult result,
			Principal principal) {
		ResponseEntity<Object> errors = responseError.mapValidationService(result);
		if (!ObjectUtils.isEmpty(errors)) {
			return errors;
		}
		Post post = service.createPost(postDTO, principal);
		PostDTO createdPost = facade.postToPostDTO(post);
		return new ResponseEntity<>(createdPost, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<PostDTO>> getAllPosts() {
		List<PostDTO> postDTOList = service.getAllPosts().stream().map(facade::postToPostDTO)
				.collect(Collectors.toList());
		return new ResponseEntity<>(postDTOList, HttpStatus.OK);
	}

	@GetMapping("/user/posts")
	public ResponseEntity<List<PostDTO>> getAllPostsForUser(Principal principal) {
		List<PostDTO> postList = service.getAllPostForUser(principal).stream().map(facade::postToPostDTO)
				.collect(Collectors.toList());
		return new ResponseEntity<>(postList, HttpStatus.OK);
	}

	@PutMapping("/{postId}/{username}/like")
	public ResponseEntity<PostDTO> likePost(@PathVariable("postId") String postId,
			@PathVariable("username") String username) {
		Post post = service.likePost(Long.parseLong(postId), username);
		PostDTO postDTO = facade.postToPostDTO(post);
		return new ResponseEntity<>(postDTO, HttpStatus.OK);
	}

	@DeleteMapping("/{postId}/delete")
	public ResponseEntity<MessageResponse> deletePost(@PathVariable("postId") String postId, Principal principal) {
		service.deletePost(Long.parseLong(postId), principal);
		return new ResponseEntity<>(new MessageResponse("Post deleted"), HttpStatus.OK);
	}
}
