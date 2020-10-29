package com.interview.template.controller;

import java.util.List;


import com.interview.template.exceptions.AlreadyExistsException;
import com.interview.template.exceptions.UserNotFoundException;
import com.interview.template.exceptions.UsernameNotAllowed;
import com.interview.template.model.UserEntity;
import com.interview.template.service.UserService;

import com.interview.template.util.HelperClass;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(UserController.BASE_URL)
@AllArgsConstructor
@Validated
public class UserController {

    @Value("${reserve.words}")
    private String[] reservedWords;

	static final String BASE_URL = "/api/v1/users";

	@Autowired
	private UserService userService;

	@GetMapping
	public List<UserEntity> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{userId}")
	public UserEntity getUser(@PathVariable Long userId) throws UserNotFoundException {
		UserEntity user = userService.getUser(userId);
		user.setPassword("");
		return userService.getUser(userId);
	}

	@RequestMapping(method = RequestMethod.HEAD, value = "/{userId}")
    public void checkExists(@PathVariable Long userId) throws UserNotFoundException {
		System.out.println("head method");
		userService.checkUserExists(userId);
	}

	//@LogExecutionTime
	@PostMapping("/create")
    public UserEntity addUser(@Valid @RequestBody UserEntity userEntity) throws UsernameNotAllowed, AlreadyExistsException {

	    String username = userEntity.getUsername();

        boolean blackListedUser = HelperClass.isBlackListedUser(username, reservedWords);

        if(blackListedUser){
            throw new UsernameNotAllowed("Username "+username+" not allowed");
        }

        String email = userEntity.getEmail();

        if(userService.findByEmail(email)!=null){
            throw new AlreadyExistsException("User with email "+email+" already exists");
        }

        if(userService.findByUsername(username)!=null){
            throw new AlreadyExistsException("User with username "+username+" already exists");
        }

    	UserEntity user = userService.create(userEntity);
		user.setPassword("");
		return user;
	}

	@DeleteMapping("/{userId}")
	public void delete(@PathVariable Long userId)  throws UserNotFoundException {
		userService.delete(userId);
	}

	@GetMapping("/username")
	public List<UserEntity> findByUsernameContaining(@RequestParam String username){
		return userService.findByUsernameContainingIgnoreCase(username);
	}
}
