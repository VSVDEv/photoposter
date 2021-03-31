package com.vsvdev.facade;

import org.springframework.stereotype.Component;

import com.vsvdev.dto.PostDTO;
import com.vsvdev.entity.Post;

@Component
public class PostFacade {

	public PostDTO postToPostDTO(Post post){
		PostDTO postDTO = new PostDTO();
		postDTO.setId(post.getId());
		postDTO.setUserName(post.getUser().getUsername());
		postDTO.setCaption(post.getCaption());
		postDTO.setLikes(post.getLikes());
		postDTO.setUsersLiked(post.getLikedUsers());
		postDTO.setLocation(post.getLocation());
		postDTO.setTitle(post.getTitle());
		return postDTO;
	}
}
