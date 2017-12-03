package com.cloud.email.servicebroker.service;

import java.io.IOException;

import org.springframework.stereotype.Service;


/**
 *         This is base class for Search Service related classes , responsible
 *         for creating search related entities.
 */
@Service
public class BaseService {


	public void sendEmail(String instanceId, String content) throws IOException {
		
		System.out.println("Send Mail --- from instance "+instanceId+" content is "+content);
	}


}
