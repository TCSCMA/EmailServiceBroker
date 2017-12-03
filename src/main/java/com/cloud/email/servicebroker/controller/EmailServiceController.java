package com.cloud.email.servicebroker.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.email.servicebroker.service.BaseService;

/**
 * @author Chandrakant Bagade
 *
 *         This class acts as REST resource for search functionality. Class will
 *         help adding documents for indexing , searching and deleting
 */
@RestController
public class EmailServiceController {

	// autowiring of search related services
	@Autowired
	BaseService emailService;

	/**
	 * add new content
	 * 
	 * @param instanceId
	 *            , service instance id , assigned by cloud foundry while
	 *            service creation
	 * @param content
	 *            , content to store , it could be plain text , JSON Object,
	 *            JSON Array in String format
	 * @return ResponseEntity having a String message and response code
	 */

	@RequestMapping(value = "/email/{instanceId}", method = RequestMethod.PUT)
	public ResponseEntity<String> sendMail(
			@PathVariable("instanceId") String instanceId,
			@RequestBody String content) {

		String methodInfo = "sendMail() :: ";

		System.out.println(methodInfo + " , instanceId " + instanceId
				+ " content , " + content);

		if (content == null || content.trim().equals("")) {
			return new ResponseEntity<>("No content is passed to store",
					HttpStatus.BAD_REQUEST);
		}

		try {

			emailService.sendEmail(instanceId, content);

		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					"There is problem with content update.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		System.out.println(methodInfo + " , returning ");
		return new ResponseEntity<>("The email is sent", HttpStatus.ACCEPTED);

	}

}
