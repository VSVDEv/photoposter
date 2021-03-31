package com.vsvdev.services;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vsvdev.dto.CommentDTO;
import com.vsvdev.entity.Comment;
import com.vsvdev.entity.Post;
import com.vsvdev.entity.User;
import com.vsvdev.exception.PostNotFoundException;
import com.vsvdev.repository.CommentRepository;
import com.vsvdev.repository.PostRepository;
import com.vsvdev.repository.UserRepository;

@Service
public class CommentService {
	public static final Logger LOG = LoggerFactory.getLogger(CommentService.class);
	private final CommentRepository commentRepo;
	private final UserRepository userRepo;
	private final PostRepository postRepo;

	@Autowired
	public CommentService(CommentRepository commentRepo, UserRepository userRepo, PostRepository postRepo) {
		this.commentRepo = commentRepo;
		this.userRepo = userRepo;
		this.postRepo = postRepo;
	}

	public Comment saveComment(Long postId, CommentDTO commentDTO, Principal principal) {
		User user = getUserByPrincipal(principal);
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new PostNotFoundException("Post can't be found for user: " + user.getEmail()));
		Comment comment = new Comment();
		comment.setPost(post);
		comment.setUserId(user.getId());
		comment.setUsername(user.getUsername());
		comment.setMessage(commentDTO.getMessage());
		LOG.info("Saving comment for post: " + post.getId());
		return commentRepo.save(comment);
	}

	public List<Comment> getAllCommentsForPost(Long postId) {
		Post post = postRepo.findById(postId).orElseThrow(() -> new PostNotFoundException("Post can't be found  "));
		List<Comment> comments = commentRepo.findAllByPost(post);
		return comments;
	}

	public void deleteComment(Long commentId) {
		Optional<Comment> comment = commentRepo.findById(commentId);
		comment.ifPresent(commentRepo::delete);
	}

	private User getUserByPrincipal(Principal principal) {
		String userName = principal.getName();
		return userRepo.findUserByUsername(userName)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found " + userName));
	}
}
