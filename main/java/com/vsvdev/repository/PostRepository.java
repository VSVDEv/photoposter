package com.vsvdev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsvdev.entity.Post;
import com.vsvdev.entity.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	List<Post> findAllByUserOrderByCreatedDateDesc(User user);

	List<Post> findAllByOrderByCreatedDateDesc();

	Optional<Post> findPostByIdAndUser(Long id, User user);
}
