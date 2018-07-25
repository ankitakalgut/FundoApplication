package com.bridgelabz.toDoApplication.userservice.model;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/*********************************************************************************
 * @author Ankita Kalgutkar
 *
 * 
 *PURPOSE:Business Entity class
 ************************************************************************************/
@Document(collection="Users")
public class User implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private boolean isVerified;
	
	public boolean isVerified() 
	{
		return isVerified;
	}
	
	public void setVerified(boolean isVerified) 
	{
		this.isVerified = isVerified;
	}

	public String getFirstName() 
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}

	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id=id;
	}
	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}
	@Override
	public String toString() 
	{
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", isVerified=" + isVerified + "]";
	}
}
























