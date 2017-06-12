package com.vvcs.pharm.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

public class Save {
	private final static String QUEUE_NAME = "test_queue";
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		//获取连接和通道
		Connection connection = Test_mq.getConnection();
		Channel channel = connection.createChannel();
		//声明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		//定义消费者
		QueueingConsumer consumer = new QueueingConsumer(channel);
		//监听队列
		channel.basicConsume(QUEUE_NAME, true, consumer);
		//获取消息
		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println(message);
			
		}
	}
}
