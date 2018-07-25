package com.bridgelabz.toDoApplication.userservice.service;

import javax.validation.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.bridgelabz.toDoApplication.RabbitMq.Mailmodel;
import com.bridgelabz.toDoApplication.RabbitMq.Producer;
import com.bridgelabz.toDoApplication.userservice.model.User;
import com.bridgelabz.toDoApplication.userservice.model.UserRegistrationDTO;
import com.bridgelabz.toDoApplication.userservice.repository.UserRepository;
import com.bridgelabz.toDoApplication.utilservice.JWToken;
import com.bridgelabz.toDoApplication.utilservice.RestPrecondition;
import com.bridgelabz.toDoApplication.utilservice.UserExceptionHandler;


/*************************************************************************************
 * @author Ankita Kalgutkar
 *
 * 
 *PURPOSE:Methods for user service implementation..
 **************************************************************************************/
@Service
@PropertySource("classpath:messages.properties")
public class UserServiceImpl implements UserService 
{
	@Autowired
	UserRepository userRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	JWToken jwToken;
	
	@Autowired
	Environment environment;
	
	@Autowired
	Mailmodel model;
	
	@Autowired
	Producer producer;
	
	//Method for Registration
	@Override
	public void userRegister(UserRegistrationDTO userRegistrationDTO) 
	{
		User user = userRepository.findByEmail(userRegistrationDTO.getEmail());
		RestPrecondition.checkNull(user);
		user = modelMapper.map(userRegistrationDTO, User.class);
		user.setVerified(false);
		user.setPassword(bCryptPasswordEncoder.encode(userRegistrationDTO.getPassword()));
		userRepository.save(user);
		String token = jwToken.createJWT("Admin", user.getEmail());
		model.setTo(user.getEmail());
		model.setSubject("Activation Link");
		model.setText(environment.getProperty("activation.link") + token);	
		producer.produceMail(model);
	}
	
	//Method for login
	@Override
	public void userLogin(String email,String password)
	{
		User user = userRepository.findByEmail(email);
		RestPrecondition.checkuser(user);	
		if (user.isVerified())
		{
			String pass= password;
			if (!bCryptPasswordEncoder.matches(pass, user.getPassword()))
			throw new UserExceptionHandler("Incorrect Password");
		} 	
		else 
		{
			throw new UserExceptionHandler("Please Activate Account First");
		}
			jwToken.createJWT("Admin", user.getEmail());
	}

	//Method to activate token
	@Override
	public void activate(String token) 
	{
		String email = jwToken.verifyToken(token);
		User user = userRepository.findByEmail(email);
		if (user == null)
			throw new UserExceptionHandler("Invalid link, please resend new activation link");
		if (user.isVerified())
			throw new UserExceptionHandler("Account Already Activated");
		user.setVerified(true);
		try 
		{
			userRepository.save(user);
		} catch (DataIntegrityViolationException | ConstraintViolationException e)
		{
			throw new UserExceptionHandler("Account activation failed, request for new Link");
		}
	}

	//Method for forgot password
	@Override
	public void forgotPassword(String email) 
	{
		User user = userRepository.findByEmail(email);
		if (user == null)
			throw new UserExceptionHandler("Incorrect email id");
		String token = jwToken.createJWT("Admin", email);
		model.setTo(email);
		model.setSubject( "Password Reset Link");
		model.setText(environment.getProperty("new.passwd.link") + token);	
		producer.produceMail(model);
	}
	
	//Method to set new password
	@Override
	public void newPassword(String token, String pass)
	{
		String email = jwToken.verifyToken(token);
		User user = userRepository.findByEmail(email);
		if (user == null)
			throw new UserExceptionHandler("Change password request cannot be processed, please try again later");
		user.setPassword(bCryptPasswordEncoder.encode(pass));
		try 
		{
			userRepository.save(user);
		} 
		catch (DataIntegrityViolationException | ConstraintViolationException e)
		{
			throw new UserExceptionHandler("Error in changing password, please try again later");
		}
	}
}