package com.vsvdev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsvdev.entity.ImageModel;

@Repository
public interface ImageRepository extends JpaRepository<ImageModel, Long> {
	Optional<ImageModel> findByUserId(Long userId);

	Optional<ImageModel> findByPostId(Long postId);
}
