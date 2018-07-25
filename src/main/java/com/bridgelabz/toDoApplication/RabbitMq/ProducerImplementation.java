package com.bridgelabz.toDoApplication.RabbitMq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
 
/************************************************************************************
 * @author Ankita Kalgutkar
 *
 * 
 *Purpose:Producer to produce the mail
 ***********************************************************************************/
@Service
public  class ProducerImplementation implements Producer
{
	@Autowired
	private AmqpTemplate amqpTemplate;

	@Value("${jsa.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${jsa.rabbitmq.routingkey}")
	private String routingKey;
	
	@Override
	public void produceMail(Mailmodel model)
	{
		amqpTemplate.convertAndSend(exchange, routingKey,model);
		System.out.println("Send msg = " + model);
	}
}