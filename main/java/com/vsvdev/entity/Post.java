package com.vsvdev.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
@Entity
@Table(name="post")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String title;
private String caption;
private String location;
private Integer likes;
@Column
@ElementCollection(targetClass = String.class)
private Set<String> likedUsers = new HashSet<>();
@ManyToOne(fetch = FetchType.LAZY)
private User user;
@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER,
mappedBy = "post", orphanRemoval = true)
private List<Comment>comments = new ArrayList<>();
@Column(updatable = false)
private LocalDateTime createdDate;

@PrePersist
protected void onCreate() {
	this.createdDate = LocalDateTime.now();
}

public Post() {
	
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getCaption() {
	return caption;
}

public void setCaption(String caption) {
	this.caption = caption;
}

public String getLocation() {
	return location;
}

public void setLocation(String location) {
	this.location = location;
}

public Integer getLikes() {
	return likes;
}

public void setLikes(Integer likes) {
	this.likes = likes;
}

public Set<String> getLikedUsers() {
	return likedUsers;
}

public void setLikedUsers(Set<String> likedUsers) {
	this.likedUsers = likedUsers;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public List<Comment> getComments() {
	return comments;
}

public void setComments(List<Comment> comments) {
	this.comments = comments;
}

public LocalDateTime getCreatedDate() {
	return createdDate;
}

public void setCreatedDate(LocalDateTime createdDate) {
	this.createdDate = createdDate;
}



}
