package com.neosoft.poc.service;

import java.util.List;

import javax.validation.Valid;

import com.neosoft.poc.payloads.UserDto;

public interface UserService {

	UserDto create(UserDto userDto);

	UserDto getSingleUser(int userId);

	List<UserDto> getAllUser();

	void deleteUser(int userId);

	UserDto updateUser(UserDto userDto, Integer userId);
	
	UserDto registerNewUser(UserDto user);
	
	
	

	
}
