package com.bridgelabz.toDoApplication.userservice.model;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/*********************************************************************************
 * @author Ankita Kalgutkar
 *
 * 
 *PURPOSE:Business Entity class
 ************************************************************************************/
public class UserRegistrationDTO 
{
	@NotNull(message = "Field should not be left empty")
	@NotEmpty(message = "Field should not be left empty")
	@Pattern(regexp = "[a-z-A-Z ]*", message = "Invalid characters in name field")
	private String firstName;

	@NotNull(message = "Field should not be left empty")
	@NotBlank(message = "Field should not be left empty")
	@Pattern(regexp = "[a-z-A-Z]*", message = "Invalid characters in name field")
	private String lastName;

	@NotNull(message = "Field should not be left empty")
	@NotBlank(message = "Field should not be left empty")
	@Email(message = "Invalid EmailId")
	private String email;

	@NotNull(message = "Field should not be left empty")
	@NotBlank(message = "Field should not be left empty")
	private String password;

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
}
