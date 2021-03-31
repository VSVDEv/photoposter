package com.vsvdev.facade;

import org.springframework.stereotype.Component;

import com.vsvdev.dto.UserDTO;
import com.vsvdev.entity.User;

@Component
public class UserFacade {

	public UserDTO userToUserDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setFirstName(user.getName());
		userDTO.setLastName(user.getLastname());
		userDTO.setUserName(user.getUsername());
		userDTO.setBiography(user.getBiography());
		return userDTO;
	}
}
