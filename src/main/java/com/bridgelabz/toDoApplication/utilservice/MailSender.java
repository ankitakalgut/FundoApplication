package com.bridgelabz.toDoApplication.utilservice;

import javax.mail.SendFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import com.bridgelabz.toDoApplication.RabbitMq.Mailmodel;

/***************************************************************************************
 * @author Ankita Kalgutkar
 *
 *
 *
 *PURPOSE:Sending the mail to the user
 ****************************************************************************************/
public class MailSender 
{
	@Autowired
	JavaMailSender javaMailSender;
	
	public void sendMail(Mailmodel mail) throws SendFailedException 
	{
		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		simpleMailMessage.setTo(mail.getTo());
		simpleMailMessage.setSubject(mail.getSubject());
		simpleMailMessage.setText(mail.getText());
		javaMailSender.send(simpleMailMessage);
	}	
}
