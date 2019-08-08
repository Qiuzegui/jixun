package com.jluzh.jixun.component;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jluzh.jixun.service.OrderService;

//@Component
@RabbitListener(queues = "ORDER_CANCEL_QUEUE")
public class OrderCancelReceiver {
	@Autowired
	OrderService orderService;
	
	@RabbitHandler
	public void handle(Long orderId){
		orderService.cancelOrder(orderId);
		System.out.println("process orderId:{"+orderId+"}");
	}
	
}
