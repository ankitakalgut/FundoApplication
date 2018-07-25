package com.bridgelabz.toDoApplication.RabbitMq;

/************************************************************************************
 * @author Ankita Kalgutkar
 *
 * 
 *purpose:Create RabbitMq Producer:
 ***********************************************************************************/

public interface Producer
{
	void produceMail(Mailmodel mail);
}