package com.vvcs.pharm.mq;

import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

public class vvcs_mq {

	private Connection connection;
	private Channel channel;
	private String replyQueueName;
	public void conn_mq(String mq_host, String mq_username, String mq_password, String mq_virtualhost)
	throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(mq_host);  
		factory.setUsername(mq_username);
		factory.setPassword(mq_password);
		factory.setVirtualHost(mq_virtualhost);
		connection = factory.newConnection();
		channel = connection.createChannel();
	}

	public void task_send(String queue_name, String message) throws IOException, InterruptedException {
		// channel.queueDeclare(queue_name, true, false, false, null);
		channel.basicPublish("", queue_name, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
	}
  
	/*
	 * public void worker_q(String queue_name,int qos,void dowork(String
	 * message)) throws IOException, InterruptedException {
	 * //channel.queueDeclare(queue_name, true, false, false, null);
	 * channel.basicQos(qos);
	 * 
	 * final Consumer consumer = new DefaultConsumer(channel) {
	 * 
	 * @Override public void handleDelivery(String consumerTag, Envelope
	 * envelope, AMQP.BasicProperties properties, byte[] body) throws
	 * IOException { String message = new String(body, "UTF-8"); try {
	 * dowork(message); } finally { channel.basicAck(envelope.getDeliveryTag(),
	 * false); } } }; boolean autoAck = false; channel.basicConsume(que_name,
	 * autoAck, consumer); }
	 */
        
	public String rpc_request(String queue_name, String message) throws IOException, InterruptedException {
		replyQueueName = channel.queueDeclare().getQueue();
		final String corrId = UUID.randomUUID().toString();
		AMQP.BasicProperties props = new AMQP.BasicProperties.Builder().correlationId(corrId).replyTo(replyQueueName).build();
		channel.basicPublish("", queue_name, props, message.getBytes("UTF-8"));
		final BlockingQueue<String> response = new ArrayBlockingQueue<String>(1);
		channel.basicConsume(replyQueueName, true, new DefaultConsumer(channel) {
		  @Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				if (properties.getCorrelationId().equals(corrId)) {
					response.offer(new String(body, "UTF-8"));
     		}
	    	}
		});

	return response.take();
	}
    
	public void close_mq() throws IOException, TimeoutException {
		channel.close();
		connection.close();
	}

}