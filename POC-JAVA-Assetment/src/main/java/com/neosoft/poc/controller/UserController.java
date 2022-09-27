package com.neosoft.poc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.poc.entity.User;
import com.neosoft.poc.payloads.ApiResponse;
import com.neosoft.poc.payloads.UserDto;
import com.neosoft.poc.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/create")
	public ResponseEntity<UserDto>addUser( @Valid @RequestBody UserDto userDto )
	{
		UserDto usrDto=this.userService.create(userDto);
		
		return new ResponseEntity<UserDto>(usrDto,HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto>getSingle(@PathVariable("userId") Integer userId)
	{
		UserDto userDto=this.userService.getSingleUser(userId);
		
		return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<UserDto>>getAll()
	{
		List<UserDto>userDto=this.userService.getAllUser();
		
		
		return new ResponseEntity<List<UserDto>>(userDto,HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable ("userId") Integer userId) {

		this.userService.deleteUser(userId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted SucessFully", true), HttpStatus.OK);

	}
	
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {

		UserDto updateUser = this.userService.updateUser(userDto, userId);

		return new ResponseEntity<UserDto>(updateUser, HttpStatus.CREATED);

	}
	}

	

	

