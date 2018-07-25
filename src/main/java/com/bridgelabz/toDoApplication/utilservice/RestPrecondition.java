package com.bridgelabz.toDoApplication.utilservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**************************************************************************************
 * @author Ankita Kalgutkar
 *
 * 
 *Purpose:RestPreConditions to check conditions
 *************************************************************************************/

public final class RestPrecondition 
{	
	@Autowired
	static	Environment environment;
	
    private  RestPrecondition() 
    {
        throw new AssertionError();
    }
    
    public static <T>  T checkNull(T resource)
    {
      if(resource==null)   
      {   
    	  return resource;
      }
      else    
    	  
      throw new UserExceptionHandler("email id already registered..............");
    }
    
    public static <T>  T checkuser(T resource)
    {
      if(resource==null)   
      {   
    	  throw new UserExceptionHandler("Invalid email id....................");
      }
      return resource;
    }
}
