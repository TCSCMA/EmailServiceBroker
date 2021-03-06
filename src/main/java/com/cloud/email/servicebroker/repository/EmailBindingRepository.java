package com.cloud.email.servicebroker.repository;

import org.springframework.data.repository.CrudRepository;

import com.cloud.email.servicebroker.model.EmailInstanceBinding;

/**
 * @author Chandrakant Bagade
 * 
 *         This class provide CRUD functionality with repository. The search
 *         service broker uses in memory database , but other databases as
 *         mogodb can be configured. We will have bare minimum functions
 *         implemented extending CrudRepository. This class persist and do CRUD
 *         operations with SearchInstanceBinding
 *
 */
public interface EmailBindingRepository extends
		CrudRepository<EmailInstanceBinding, String> {
}
