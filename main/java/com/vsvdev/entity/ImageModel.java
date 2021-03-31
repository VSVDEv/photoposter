package com.vsvdev.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="image")
public class ImageModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
	@Column(nullable = false)
private String name;
	@Lob
	@Type(type="org.hibernate.type.BinaryType")
	private byte[] imageBytes;
	@JsonIgnore
	private Long userId;
	@JsonIgnore
	private Long postId;
	
	
	public ImageModel() {
		}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getImageBytes() {
		return imageBytes;
	}
	public void setImageBytes(byte[] imageBytes) {
		this.imageBytes = imageBytes;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	
	
	
}
