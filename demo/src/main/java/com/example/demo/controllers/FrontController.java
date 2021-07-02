package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Users;
import com.example.demo.entities.repositories.UserRepository;
import com.example.demo.models.CreateUserRequestModel;
import com.example.demo.services.UserService;
import com.example.demo.shared.UserDto;

@RestController
@RequestMapping("/users")
public class FrontController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@GetMapping("/hello")
	@ResponseBody
	public List<Users> getAll() {
		return (List<Users>) userRepository.findAll();

	}

	@GetMapping("/{id}")
	@Cacheable(cacheNames= {"users"}, key="#id")
	public Users getByid(@PathVariable String id)
	{
		return userRepository.findByUserId(id);
	}
	
	
	@Scheduled(fixedDelay=20000)
	@GetMapping("/leaderboard")
	@ResponseBody
	public List<Users> getLeaderBoard() {
	
		System.out.println("fetching the leaderboard!!!");
		return userRepository.getLeaderBoard();
	
	}

	@PostMapping(path = "/save", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })

	//@ResponseBody
	public String createUser(@RequestBody CreateUserRequestModel userDetails) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		//userDto.setPassword("12345");
		UserDto createdUser = userService.createUser(userDto);

		System.out.println(createdUser.getEncryptedPassword());
		// userRepository.save(userDetails);

		return "USER CREATED";

	}

}
