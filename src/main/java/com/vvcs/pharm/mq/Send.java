package com.vvcs.pharm.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {
	private final static String QUEUE_NAME = "test_queue";
	public static void main(String[] args) throws Exception {
		//获取连接
		Connection connection = Test_mq.getConnection();
		
		//创建通道
		Channel channel = connection.createChannel();
		
		//声明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		//消息内容 
		String message="Hello World!";
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		System.out.println("Send"+message+";");
		
		//关闭通道和连接
		channel.close();
		connection.close();
	}
}
