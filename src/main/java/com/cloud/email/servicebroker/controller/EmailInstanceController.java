package com.cloud.email.servicebroker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.servicebroker.exception.ServiceInstanceDoesNotExistException;
import org.springframework.cloud.servicebroker.exception.ServiceInstanceExistsException;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceResponse;
import org.springframework.cloud.servicebroker.model.DeleteServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.DeleteServiceInstanceResponse;
import org.springframework.cloud.servicebroker.model.GetLastServiceOperationRequest;
import org.springframework.cloud.servicebroker.model.GetLastServiceOperationResponse;
import org.springframework.cloud.servicebroker.model.OperationState;
import org.springframework.cloud.servicebroker.model.UpdateServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.UpdateServiceInstanceResponse;
import org.springframework.cloud.servicebroker.service.ServiceInstanceService;
import org.springframework.stereotype.Service;

import com.cloud.email.servicebroker.model.EmailInstance;
import com.cloud.email.servicebroker.repository.EmailInstanceRepository;

/**
 *         This class lists method to create , delete service instance. The
 *         methods listed will be called by cloud foundry platform passing
 *         proper service instance ids
 *
 */

@Service
public class EmailInstanceController implements ServiceInstanceService {

	@Autowired
	private EmailInstanceRepository repository;

	@Autowired
	public EmailInstanceController(EmailInstanceRepository repository) {		
		this.repository = repository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.cloud.servicebroker.service.ServiceInstanceService
	 * #createServiceInstance
	 * (org.springframework.cloud.servicebroker.model.CreateServiceInstanceRequest
	 * )
	 * 
	 * 
	 * path PUT /v2/service_instances/{instance_id}
	 */
	@Override
	public CreateServiceInstanceResponse createServiceInstance(
			CreateServiceInstanceRequest request) {

		String instanceId = request.getServiceInstanceId();
		System.out
				.println("createServiceInstance () :: Creating service instance "
						+ instanceId);
		EmailInstance instance = repository.findOne(instanceId);
		if (instance != null) {
			throw new ServiceInstanceExistsException(instanceId,
					request.getServiceDefinitionId());
		}

		instance = new EmailInstance(request);
		System.out
				.println("createServiceInstance() :: service instance created "
						+ instance);

		System.out
				.println("createServiceInstance() :: email service instance created");

		repository.save(instance);
		return new CreateServiceInstanceResponse();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.cloud.servicebroker.service.ServiceInstanceService
	 * #getLastOperation
	 * (org.springframework.cloud.servicebroker.model.GetLastServiceOperationRequest
	 * )
	 */
	@Override
	public GetLastServiceOperationResponse getLastOperation(
			GetLastServiceOperationRequest request) {
		return new GetLastServiceOperationResponse()
				.withOperationState(OperationState.SUCCEEDED);
	}

	public EmailInstance getServiceInstance(String id) {
		return repository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.cloud.servicebroker.service.ServiceInstanceService
	 * #deleteServiceInstance
	 * (org.springframework.cloud.servicebroker.model.DeleteServiceInstanceRequest
	 * )
	 * 
	 * path DELETE /v2/service_instances/{instance_id}
	 */
	@Override
	public DeleteServiceInstanceResponse deleteServiceInstance(
			DeleteServiceInstanceRequest request) {

		String instanceId = request.getServiceInstanceId();

		System.out
				.println("deleteServiceInstance () :: deleting service instance "
						+ instanceId);

		EmailInstance instance = repository.findOne(instanceId);

		if (instance == null) {
			throw new ServiceInstanceDoesNotExistException(instanceId);
		}

		//baseService.deleteSearchEntity(instanceId);
		repository.delete(instanceId);
		return new DeleteServiceInstanceResponse();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.cloud.servicebroker.service.ServiceInstanceService
	 * #updateServiceInstance
	 * (org.springframework.cloud.servicebroker.model.UpdateServiceInstanceRequest
	 * )
	 */
	@Override
	public UpdateServiceInstanceResponse updateServiceInstance(
			UpdateServiceInstanceRequest request) {
		String instanceId = request.getServiceInstanceId();
		EmailInstance instance = repository.findOne(instanceId);
		if (instance == null) {
			throw new ServiceInstanceDoesNotExistException(instanceId);
		}

		repository.delete(instanceId);
		EmailInstance updatedInstance = new EmailInstance(request);

		/*try {
			baseService.createSearchEntity(request.getServiceInstanceId());
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		repository.save(updatedInstance);
		return new UpdateServiceInstanceResponse();
	}

}