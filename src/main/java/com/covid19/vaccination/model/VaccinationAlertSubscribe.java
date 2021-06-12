/**
 * 
 */
package com.covid19.vaccination.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author ab68478
 *
 */
@Document
public class VaccinationAlertSubscribe {
	@Id
	private ObjectId id;
	private String email;
	private String state;
	private String district;
	private boolean under45;
	private boolean above45;
	private boolean dose1Subscription;
	private boolean dose2Subscription;

	/**
	 * @return the id
	 */
	public ObjectId getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(ObjectId id) {
		this.id = id;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the under45
	 */
	public boolean isUnder45() {
		return under45;
	}

	/**
	 * @param under45 the under45 to set
	 */
	public void setUnder45(boolean under45) {
		this.under45 = under45;
	}

	/**
	 * @return the above45
	 */
	public boolean isAbove45() {
		return above45;
	}

	/**
	 * @param above45 the above45 to set
	 */
	public void setAbove45(boolean above45) {
		this.above45 = above45;
	}

	/**
	 * @return the dose1Subscription
	 */
	public boolean isDose1Subscription() {
		return dose1Subscription;
	}

	/**
	 * @param dose1Subscription the dose1Subscription to set
	 */
	public void setDose1Subscription(boolean dose1Subscription) {
		this.dose1Subscription = dose1Subscription;
	}

	/**
	 * @return the dose2Subscription
	 */
	public boolean isDose2Subscription() {
		return dose2Subscription;
	}

	/**
	 * @param dose2Subscription the dose2Subscription to set
	 */
	public void setDose2Subscription(boolean dose2Subscription) {
		this.dose2Subscription = dose2Subscription;
	}

}
