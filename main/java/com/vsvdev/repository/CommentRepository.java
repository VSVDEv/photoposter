package com.vsvdev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsvdev.entity.Comment;

import com.vsvdev.entity.Post;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findAllByPost(Post post);

	Comment findByIdAndUserId(Long commentId, Long userId);
}
