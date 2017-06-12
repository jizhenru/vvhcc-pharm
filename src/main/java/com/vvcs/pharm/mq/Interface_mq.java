package com.vvcs.pharm.mq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


//连接MQ
public class Interface_mq {

	public String SendMessage(String queue_name, String message) {
		String result = null;
		vvcs_mq rpcclient;
		try {
			rpcclient = new vvcs_mq();
			rpcclient.conn_mq("mq01.vvcs.com", "vvadmin", "vvcs", "/");

			try {
				result = rpcclient.rpc_request(queue_name, message);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			rpcclient.close_mq();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (TimeoutException e) {

			e.printStackTrace();
		}

		return result;
	}
}
