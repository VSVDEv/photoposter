package com.vsvdev.dto;

import java.util.Set;

public class PostDTO {

	private Long id;
	private String title;
	private String caption;
	private String location;
	private String userName;
	private Integer likes;
	private Set<String> usersLiked;
	public PostDTO(Long id, String title, String caption, String location, String userName, Integer likes,
			Set<String> usersLiked) {
				this.id = id;
		this.title = title;
		this.caption = caption;
		this.location = location;
		this.userName = userName;
		this.likes = likes;
		this.usersLiked = usersLiked;
	}
	public PostDTO() {
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getLikes() {
		return likes;
	}
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	public Set<String> getUsersLiked() {
		return usersLiked;
	}
	public void setUsersLiked(Set<String> usersLiked) {
		this.usersLiked = usersLiked;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caption == null) ? 0 : caption.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((likes == null) ? 0 : likes.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((usersLiked == null) ? 0 : usersLiked.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PostDTO other = (PostDTO) obj;
		if (caption == null) {
			if (other.caption != null)
				return false;
		} else if (!caption.equals(other.caption))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (likes == null) {
			if (other.likes != null)
				return false;
		} else if (!likes.equals(other.likes))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (usersLiked == null) {
			if (other.usersLiked != null)
				return false;
		} else if (!usersLiked.equals(other.usersLiked))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PostDTO [id=" + id + ", title=" + title + ", caption=" + caption + ", location=" + location
				+ ", userName=" + userName + ", likes=" + likes + ", usersLiked=" + usersLiked + "]";
	}
	
	
}
