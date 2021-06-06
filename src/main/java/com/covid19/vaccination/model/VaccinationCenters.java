/**
 * 
 */
package com.covid19.vaccination.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author ab68478
 *
 */

@Document
public class VaccinationCenters {

	private String center_id;
	private String name;
	private String pincode;

	private List<Sessions> sessions;

	/**
	 * @return the center_id
	 */
	public String getCenter_id() {
		return center_id;
	}

	/**
	 * @param center_id the center_id to set
	 */
	public void setCenter_id(String center_id) {
		this.center_id = center_id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the sessions
	 */
	public List<Sessions> getSessions() {
		return sessions;
	}

	/**
	 * @param sessions the sessions to set
	 */
	public void setSessions(List<Sessions> sessions) {
		this.sessions = sessions;
	}

	/**
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}

	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

}
