package com.neosoft.poc.serviceIMPL;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.neosoft.poc.config.AppConstants;
import com.neosoft.poc.entity.Role;
import com.neosoft.poc.entity.User;
import com.neosoft.poc.exception.ResourceNotFoundException;
import com.neosoft.poc.payloads.UserDto;
import com.neosoft.poc.repository.RoleRepository;
import com.neosoft.poc.repository.UserRepository;
import com.neosoft.poc.service.UserService;


@Service
public class UserServiceIMPL implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto create(UserDto userDto) {
		
		User user=this.modelMapper.map(userDto,User.class);
		
		User savedUser = this.userRepository.save(user);

		return this.modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public UserDto getSingleUser(int userId) {
		
		User user=this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with " +  userId  + " not present on server!!"));
		
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> user=this.userRepository.findAll();
		
		List<UserDto>userDto=user.stream().map((usr)->this.modelMapper.map(usr, UserDto.class)).collect(Collectors.toList());
		
		return userDto;
	}

	@Override
	public void deleteUser(int userId) {
	
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with " + userId + " not present on server!!"));

		this.userRepository.delete(user);
		
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {

		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with " + userId + " ont present on server!!"));

		user.setAdharCard(userDto.getAdharCard());
		user.setCity(userDto.getCity());
		user.setConfirmPassword(userDto.getConfirmPassword());
		user.setCountry(userDto.getCountry());
		user.setDistrict(userDto.getDistrict());
		user.setEmailId(userDto.getEmailId());
		user.setFullName(userDto.getFullName());
		user.setMobileNo(userDto.getMobileNo());
		user.setPanCard(userDto.getPanCard());
		user.setPassword(userDto.getPassword());
		user.setPinCode(userDto.getPinCode());
		user.setState(userDto.getState());
		user.setStatus(true);
		user.setUserType(userDto.getUserType());

		User updatedUser = this.userRepository.save(user);

		return this.modelMapper.map(updatedUser, UserDto.class);
	}

	
	
	
	@Override
	public UserDto registerNewUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);

		// encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		// roles
		Role role = this.roleRepository.findById(AppConstants.NORMAL_USER).get();

		user.getRoles().add(role);

		User newUser = this.userRepository.save(user);

		return this.modelMapper.map(newUser, UserDto.class);

	}

	
	
	

	
}
