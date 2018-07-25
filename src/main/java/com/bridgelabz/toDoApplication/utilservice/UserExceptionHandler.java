package com.bridgelabz.toDoApplication.utilservice;

/*********************************************************************************
 * @author Ankita Kalgutkar
 *
 * 
 *PURPOSE:To catch the UserException that occur during user operations.
 ************************************************************************************/
public class UserExceptionHandler extends RuntimeException
{
	private static final long serialVersionUID = -359309893066954824L;

	public UserExceptionHandler(String message)
	{
		super(message);
	}
}
