package com.vsvdev.controller;

import java.io.IOException;
import java.security.Principal;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vsvdev.entity.ImageModel;
import com.vsvdev.payload.MessageResponse;
import com.vsvdev.services.ImageService;

@RestController
@RequestMapping("api/images")
@CrossOrigin
public class ImageController {

	private final ImageService service;

	@Autowired
	public ImageController(ImageService service) {
		this.service = service;
	}

	@PostMapping("/upload")
	public ResponseEntity<MessageResponse> uploadImageToUser(@RequestParam("file") MultipartFile file,
			Principal principal) throws IOException {
		service.uploadImageToUser(file, principal);
		return ResponseEntity.ok(new MessageResponse("Image loaded successfully"));
	}

	@PostMapping("/{postId}/upload")
	public ResponseEntity<MessageResponse> uploadImageToPost(@PathVariable("postId") String postId,
			@RequestParam("file") MultipartFile file, Principal principal) throws IOException {
		service.uploadImageToPost(file, principal, Long.parseLong(postId));
		return ResponseEntity.ok(new MessageResponse("Image loaded successfully"));
	}

	@GetMapping("/profileImage")
	public ResponseEntity<ImageModel> getImageToUser(Principal principal) {
		ImageModel imageUser = service.getImageToUser(principal);
		return new ResponseEntity<>(imageUser, HttpStatus.OK);

	}

	@GetMapping("/{postId}/image")
	public ResponseEntity<ImageModel> getImageToPost(@PathVariable("postId") String postId) {
		ImageModel imagePost = service.getImageToPost(Long.parseLong(postId));
		return new ResponseEntity<>(imagePost, HttpStatus.OK);

	}
}
