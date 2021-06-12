/**
 * 
 */
package com.covid19.vaccination.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author ab68478
 *
 */
@Document
public class VaccinationInfo {

	@Id
	private ObjectId id;
	private List<VaccinationCenters> centers;
	private String date;
	private String district;

	/**
	 * @return the centers
	 */
	public List<VaccinationCenters> getCenters() {
		return centers;
	}

	/**
	 * @param centers the centers to set
	 */
	public void setCenters(List<VaccinationCenters> centers) {
		this.centers = centers;
	}

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
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
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

}
