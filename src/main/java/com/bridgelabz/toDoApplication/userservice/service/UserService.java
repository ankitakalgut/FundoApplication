package com.bridgelabz.toDoApplication.userservice.service;

import com.bridgelabz.toDoApplication.userservice.model.UserRegistrationDTO;

/***************************************************************************
 * @author Ankita Kalgutkar
 *
 * 
 *Purpose:Method declarations 
 ****************************************************************************/
public interface UserService
{
	public void userRegister(UserRegistrationDTO userRegistrationDTO) ;

	public void activate(String token);

	public void forgotPassword(String email);

	public void newPassword(String token, String pass);
	
	public void userLogin(String email, String password);	
}
