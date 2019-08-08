package com.jluzh.jixun.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RabbitMqConfig {
	public static final String ORDER_CANCEL_QUEUE="ORDER_CANCEL_QUEUE";
	public static final String ORDER_CANCEL_EXCHANGE="ORDER_CANCEL_EXCHANGE";
	public static final String ORDER_CANCEL_KEY="ORDER_CANCEL_KEY";
	
	public static final String ORDER_TTL_QUEUE="ORDER_DELETE_TTL_QUEUE";
	public static final String ORDER_TTL_EXCHANGE="ORDER_TTL_EXCHANGE";
	public static final String ORDER_TTL_KEY="ORDER_TTL_KEY";
	/**
	 * 订单队列
	 * @return
	 */
	@Bean
	public Queue orderQueue(){
		return new Queue(ORDER_CANCEL_QUEUE);
	}
	/**
	 * 死信队列
	 */
	@Bean
	public Queue ttlQueue(){
		return QueueBuilder.durable(ORDER_TTL_QUEUE)
						.withArgument("x-dead-letter-exchange", ORDER_CANCEL_QUEUE)
						.withArgument("x-dead-letter-routing-key",ORDER_CANCEL_KEY)
						.build();
	}
	/**
	 * 订单队列交换机
	 * @return
	 */
	@Bean
    DirectExchange orderDirect(){
		return (DirectExchange) ExchangeBuilder
				.directExchange(ORDER_CANCEL_EXCHANGE)
				.durable(true)
				.build();
	}
	/**
	 * 死信队列交换机
	 * @return
	 */
	@Bean
	DirectExchange ttlDirect(){
		return (DirectExchange) ExchangeBuilder
				.directExchange(ORDER_TTL_EXCHANGE)
				.durable(true)
				.build();
	}
	
	/**
	 * 将订单队列绑定至订单队列交换机
	 */
	@Bean
	Binding orderBinding(DirectExchange orderDirect,Queue orderQueue){
		return BindingBuilder
				.bind(orderQueue)
				.to(orderDirect)
				.with(ORDER_CANCEL_KEY);
	}
	/**
	 * 将死信队列绑定至死信队列交换机
	 */
	@Bean
	Binding ttlBinding(DirectExchange ttlDirect,Queue ttlQueue){
		return BindingBuilder
				.bind(ttlQueue)
				.to(ttlDirect)
				.with(ORDER_TTL_KEY);
	}
}
