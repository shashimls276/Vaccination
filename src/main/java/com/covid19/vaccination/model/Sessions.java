/**
 * 
 */
package com.covid19.vaccination.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author ab68478
 *
 */
@Document
public class Sessions {
	private String available_capacity;
	private String min_age_limit;
	private String available_capacity_dose1;
	private String available_capacity_dose2;

	/**
	 * @return the available_capacity
	 */
	public String getAvailable_capacity() {
		return available_capacity;
	}

	/**
	 * @param available_capacity the available_capacity to set
	 */
	public void setAvailable_capacity(String available_capacity) {
		this.available_capacity = available_capacity;
	}

	/**
	 * @return the min_age_limit
	 */
	public String getMin_age_limit() {
		return min_age_limit;
	}

	/**
	 * @param min_age_limit the min_age_limit to set
	 */
	public void setMin_age_limit(String min_age_limit) {
		this.min_age_limit = min_age_limit;
	}

	/**
	 * @return the available_capacity_dose1
	 */
	public String getAvailable_capacity_dose1() {
		return available_capacity_dose1;
	}

	/**
	 * @param available_capacity_dose1 the available_capacity_dose1 to set
	 */
	public void setAvailable_capacity_dose1(String available_capacity_dose1) {
		this.available_capacity_dose1 = available_capacity_dose1;
	}

	/**
	 * @return the available_capacity_dose2
	 */
	public String getAvailable_capacity_dose2() {
		return available_capacity_dose2;
	}

	/**
	 * @param available_capacity_dose2 the available_capacity_dose2 to set
	 */
	public void setAvailable_capacity_dose2(String available_capacity_dose2) {
		this.available_capacity_dose2 = available_capacity_dose2;
	}

}
