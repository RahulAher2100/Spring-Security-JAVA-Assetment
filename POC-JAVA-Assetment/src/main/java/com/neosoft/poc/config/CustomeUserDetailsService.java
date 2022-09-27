package com.neosoft.poc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.neosoft.poc.entity.User;
import com.neosoft.poc.exception.ResourceNotFoundException;
import com.neosoft.poc.repository.UserRepository;


@Service
public class CustomeUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// loading user from database by username
			 User user = this.userRepository.findByEmailId(username).orElseThrow(()-> new ResourceNotFoundException("User Not Found !!"));
						
		
	          return user;
	}

	
}
