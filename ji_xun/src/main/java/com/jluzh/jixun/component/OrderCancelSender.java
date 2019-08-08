package com.jluzh.jixun.component;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jluzh.jixun.config.RabbitMqConfig;

//@Component
public class OrderCancelSender {
	@Autowired
	private AmqpTemplate amqpTemplete;
	
	public void sendMsg(Long orderId,final long ttl){
		amqpTemplete.convertSendAndReceive(RabbitMqConfig.ORDER_TTL_EXCHANGE, RabbitMqConfig.ORDER_TTL_KEY, orderId,new MessagePostProcessor(){
			
			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				//给消息设置延迟毫秒值
				message.getMessageProperties().setExpiration(String.valueOf(ttl));
                return message;
			}
			
		});
	}
}
