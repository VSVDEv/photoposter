package com.vsvdev.facade;

import org.springframework.stereotype.Component;

import com.vsvdev.dto.CommentDTO;
import com.vsvdev.entity.Comment;

@Component
public class CommentFacade {

	public CommentDTO  commentToCommentDTO(Comment comment) {
		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setId(comment.getId());
		commentDTO.setUserName(comment.getUsername());
		commentDTO.setMessage(comment.getMessage());
		return commentDTO;
	}
}
