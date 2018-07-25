package com.bridgelabz.toDoApplication.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.toDoApplication.userservice.model.UserRegistrationDTO;
import com.bridgelabz.toDoApplication.userservice.service.UserServiceImpl;
import com.bridgelabz.toDoApplication.utilservice.Response;

/******************************************************************************************
 * @author Ankita Kalgutkar
 *
 *
 *
 *Purpose:Controller class
 *****************************************************************************************/
@RestController
//Informs the spring to render the result back to the caller
public class UserController 
{
	@Autowired
	UserServiceImpl userService;

	//For registration
	//Tells spring to map any Http req to particular method.
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	private ResponseEntity<Response> register(@RequestBody UserRegistrationDTO userRegistrationDTO) throws Exception 
	{
		userService.userRegister(userRegistrationDTO);
		return new ResponseEntity<>(new Response("Registration Success", HttpStatus.CREATED), HttpStatus.OK);
	}

	//For login
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Response> login(@RequestParam("email") String email,@RequestParam("password") String password)
	{
		userService.userLogin(email,password);
		return new ResponseEntity<>(new Response("Login Success", HttpStatus.OK), HttpStatus.OK);
	}
	
	//To activate Token
	@RequestMapping(value = "/activate/{token}", method = RequestMethod.GET)
	public ResponseEntity<Response> activation(@PathVariable String token) 
	{
		userService.activate(token);
		return new ResponseEntity<>(new Response("Account Activated", HttpStatus.OK), HttpStatus.OK);
	}

	//Forgot password
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public ResponseEntity<Response> forgetpassword(@RequestParam("email") String email)
	{
		userService.forgotPassword(email);
		return new ResponseEntity<>(new Response("Password Reset Link Sent", HttpStatus.OK), HttpStatus.OK);
	}

	//new password
	@RequestMapping(value = "/resetpassword/{token}", method = RequestMethod.POST)
	public ResponseEntity<Response> newPassword(@PathVariable String token, @RequestParam("password") String password)
	{
		userService.newPassword(token, password);
		return new ResponseEntity<>(new Response("Password Changed Succesfully", HttpStatus.OK), HttpStatus.OK);
	}
}

