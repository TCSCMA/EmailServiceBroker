package com.cloud.email.servicebroker.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.cloud.servicebroker.model.CreateServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.DeleteServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.UpdateServiceInstanceRequest;

/**
 *         Email service instance
 * 
 */

@Entity
public class EmailInstance {

	@Id
	private String id;
	private String serviceDefinitionId;
	private String planId;
	private String organizationGuid;
	private String spaceGuid;
	

	@SuppressWarnings("unused")
	private EmailInstance() {
	}

	/**
	 * Create a ServiceInstance from a create request. If fields are not present
	 * in the request they will remain null in the ServiceInstance.
	 * 
	 * @param request
	 *            containing details of ServiceInstance
	 */
	public EmailInstance(CreateServiceInstanceRequest request) {
		this.serviceDefinitionId = request.getServiceDefinitionId();
		this.planId = request.getPlanId();
		this.organizationGuid = request.getOrganizationGuid();
		this.spaceGuid = request.getSpaceGuid();
		this.id = request.getServiceInstanceId();
	}

	/**
	 * Create a ServiceInstance from a delete request. If fields are not present
	 * in the request they will remain null in the ServiceInstance.
	 * 
	 * @param request
	 *            containing details of ServiceInstance
	 */
	public EmailInstance(DeleteServiceInstanceRequest request) {
		this.id = request.getServiceInstanceId();
		this.planId = request.getPlanId();
		this.serviceDefinitionId = request.getServiceDefinitionId();
	}

	/**
	 * Create a service instance from an update request. If fields are not
	 * present in the request they will remain null in the ServiceInstance.
	 * 
	 * @param request
	 *            containing details of ServiceInstance
	 */
	public EmailInstance(UpdateServiceInstanceRequest request) {
		this.id = request.getServiceInstanceId();
		this.planId = request.getPlanId();
	}

	public String getServiceInstanceId() {
		return id;
	}

	public String getServiceDefinitionId() {
		return serviceDefinitionId;
	}

	public String getPlanId() {
		return planId;
	}

	public String getOrganizationGuid() {
		return organizationGuid;
	}

	public String getSpaceGuid() {
		return spaceGuid;
	}

	public EmailInstance and() {
		return this;
	}
	
	public void sendMail(String content) {
		System.out.println("Send Mail --- from instance "+id+" content is "+content);
	}

	@Override
	public String toString() {
		return "ServiceInstance [id=" + id + ", serviceDefinitionId="
				+ serviceDefinitionId + ", planId=" + planId
				+ ", organizationGuid=" + organizationGuid + ", spaceGuid="
				+ spaceGuid + "]";
	}

}
