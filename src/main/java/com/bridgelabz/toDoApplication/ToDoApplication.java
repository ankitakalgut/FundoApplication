package com.bridgelabz.toDoApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/********************************************************
 * @author Ankita Kalgutkar
 *
 * 
 *********************************************************/
@SpringBootApplication
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class ToDoApplication 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(ToDoApplication.class, args);
	}
}
