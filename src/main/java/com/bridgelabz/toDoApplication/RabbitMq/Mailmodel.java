package com.bridgelabz.toDoApplication.RabbitMq;

import org.springframework.stereotype.Service;

/*****************************************************************************
 * @author Ankita Kalgutkar
 *
 * 
 *Purpose:Mail Entity class
 *****************************************************************************/
@Service
public class Mailmodel 
{
	private String to;
	private String subject;
	private String text;
	
	public String getTo() 
	{
		return to;
	}
	
	public void setTo(String to) 
	{
		this.to = to;
	}
	
	public String getSubject() 
	{
		return subject;
	}
	
	public void setSubject(String subject) 
	{
		this.subject = subject;
	}
	
	public String getText() 
	{
		return text;
	}
	
	public void setText(String text) 
	{
		this.text = text;
	}
	
	@Override
	public String toString()
	{
		return "Mailmodel [to=" + to + ", subject=" + subject + ", text=" + text + "]";
	}
}
