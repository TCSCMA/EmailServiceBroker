package com.cloud.email.servicebroker.repository;

import org.springframework.data.repository.CrudRepository;

import com.cloud.email.servicebroker.model.EmailInstance;

/**
 * @author Chandrakant Bagade
 * 
 *         This class to provides CRUD functionality with repository. The search
 *         service broker uses in memory database , but other databases as
 *         mogodb can be configured.We will have bare minimum functions
 *         implemented extending CrudRepository. This class persist and do CRUD
 *         operations with SearchInstance
 *
 *
 */
public interface EmailInstanceRepository extends
		CrudRepository<EmailInstance, String> {
}
