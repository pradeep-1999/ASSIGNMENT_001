package com.example.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.shared.UserDto;

@Service
public interface UserService extends UserDetailsService {

	UserDto createUser(UserDto userDetails);

	// void sortEntity();
	UserDto getUserDetailsByEmail(String email);

	UserDto getUserByUserId(String userId);

	//UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
