package com.vsvdev.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
@Entity
@Table(name="comment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
	@ManyToOne(fetch = FetchType.EAGER )
private Post post;
	@Column(nullable = false)
private String username;
	@Column(nullable = false)
private Long userId;
	@Column(columnDefinition = "text",nullable = false )
private String message;
@Column(updatable = false)
private LocalDateTime createdDate;

@PrePersist
protected void onCreate() {
	this.createdDate = LocalDateTime.now();
}

public Comment() {
	
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Post getPost() {
	return post;
}

public void setPost(Post post) {
	this.post = post;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public Long getUserId() {
	return userId;
}

public void setUserId(Long userId) {
	this.userId = userId;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

public LocalDateTime getCreatedDate() {
	return createdDate;
}

public void setCreatedDate(LocalDateTime createdDate) {
	this.createdDate = createdDate;
}


}
