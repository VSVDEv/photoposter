package com.vsvdev.services;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vsvdev.dto.PostDTO;
import com.vsvdev.entity.ImageModel;
import com.vsvdev.entity.Post;
import com.vsvdev.entity.User;
import com.vsvdev.exception.PostNotFoundException;
import com.vsvdev.repository.ImageRepository;
import com.vsvdev.repository.PostRepository;
import com.vsvdev.repository.UserRepository;

@Service
public class PostService {
	public static final Logger LOG = LoggerFactory.getLogger(PostService.class);

	private final PostRepository postRepo;
	private final UserRepository userRepo;
	private final ImageRepository imageRepo;

	@Autowired
	public PostService(PostRepository postRepo, UserRepository userRepo, ImageRepository imageRepo) {
		this.postRepo = postRepo;
		this.userRepo = userRepo;
		this.imageRepo = imageRepo;
	}

	public Post createPost(PostDTO postDTO, Principal principal) {
		User user = getUserByPrincipal(principal);
		Post post = new Post();
		post.setUser(user);
		post.setCaption(postDTO.getCaption());
		post.setLocation(postDTO.getLocation());
		post.setTitle(postDTO.getTitle());
		post.setLikes(0);
		LOG.info("save post for user: " + user.getEmail());
		return postRepo.save(post);
	}

	public List<Post> getAllPosts() {
		return postRepo.findAllByOrderByCreatedDateDesc();
	}

	public List<Post> getAllPostForUser(Principal principal) {
		User user = getUserByPrincipal(principal);
		return postRepo.findAllByUserOrderByCreatedDateDesc(user);
	}

	public Post getPostById(Long postId, Principal principal) {
		User user = getUserByPrincipal(principal);
		return postRepo.findPostByIdAndUser(postId, user)
				.orElseThrow(() -> new PostNotFoundException("Post can't be found for user: " + user.getEmail()));
	}

	public Post likePost(Long postId, String userName) {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new PostNotFoundException("Post can't be found for id: " + postId));
		Optional<String> userLiked = post.getLikedUsers().stream().filter(u -> u.equals(userName)).findAny();
		if (userLiked.isPresent()) {
			post.setLikes(post.getLikes() - 1);
			post.getLikedUsers().remove(userName);
		} else {
			post.setLikes(post.getLikes() + 1);
			post.getLikedUsers().add(userName);
		}
		return postRepo.save(post);
	}

	public void deletePost(Long postId, Principal principal) {
		Post post = getPostById(postId, principal);
		Optional<ImageModel> image = imageRepo.findByPostId(post.getId());
		postRepo.delete(post);
		image.ifPresent(imageRepo::delete);
	}

	private User getUserByPrincipal(Principal principal) {
		String userName = principal.getName();
		return userRepo.findUserByUsername(userName)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found " + userName));
	}

}