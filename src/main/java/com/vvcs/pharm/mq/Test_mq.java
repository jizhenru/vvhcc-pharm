package com.vvcs.pharm.mq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Test_mq {
	public static Connection getConnection() throws Exception{
		//连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		//服务地址
		factory.setHost("127.0.0.1");
		//端口
		factory.setPort(5672);
		//账号信息
		factory.setVirtualHost("JZRtest");
		factory.setUsername("JZR");
		factory.setPassword("123456");
		
		//获取连接
		Connection connection = factory.newConnection();
		return connection;
	}
}
