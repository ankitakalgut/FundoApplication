package com.bridgelabz.toDoApplication.RabbitMq;

import javax.mail.SendFailedException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.toDoApplication.utilservice.MailSender;
import com.bridgelabz.toDoApplication.RabbitMq.Mailmodel;
 
/***********************************************************************************************
 * @author Ankita Kalgutkar
 *
 * 
 *Purpose:Consumer consumes the messages.
 **********************************************************************************************/

@Service
public class Consumer 
{
	@Autowired
	MailSender javaMailSender;
	@RabbitListener(queues="${jsa.rabbitmq.queue}")
    public void recievedMessage(Mailmodel mail) throws SendFailedException 
	{  
		javaMailSender.sendMail(mail);
	}
}
