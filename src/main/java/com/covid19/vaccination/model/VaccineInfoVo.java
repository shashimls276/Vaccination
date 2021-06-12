/**
 * 
 */
package com.covid19.vaccination.model;

import org.bson.types.ObjectId;

/**
 * @author ab68478
 *
 */
public class VaccineInfoVo {
	private ObjectId id;
	private String vaccinationCenter;
	private String pincode;
	private String ageLimit;
	private String availableFirstDoseVaccine;
	private String availableSecondDoseVaccine;
	private String date;
	private String availableCapacity;
	private String vaccineName;

	/**
	 * @return the vaccinationCenter
	 */
	public String getVaccinationCenter() {
		return vaccinationCenter;
	}

	/**
	 * @param vaccinationCenter the vaccinationCenter to set
	 */
	public void setVaccinationCenter(String vaccinationCenter) {
		this.vaccinationCenter = vaccinationCenter;
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

	/**
	 * @return the ageLimit
	 */
	public String getAgeLimit() {
		return ageLimit;
	}

	/**
	 * @param ageLimit the ageLimit to set
	 */
	public void setAgeLimit(String ageLimit) {
		this.ageLimit = ageLimit;
	}

	/**
	 * @return the availableFirstDoseVaccine
	 */
	public String getAvailableFirstDoseVaccine() {
		return availableFirstDoseVaccine;
	}

	/**
	 * @param availableFirstDoseVaccine the availableFirstDoseVaccine to set
	 */
	public void setAvailableFirstDoseVaccine(String availableFirstDoseVaccine) {
		this.availableFirstDoseVaccine = availableFirstDoseVaccine;
	}

	/**
	 * @return the availableSecondDoseVaccine
	 */
	public String getAvailableSecondDoseVaccine() {
		return availableSecondDoseVaccine;
	}

	/**
	 * @param availableSecondDoseVaccine the availableSecondDoseVaccine to set
	 */
	public void setAvailableSecondDoseVaccine(String availableSecondDoseVaccine) {
		this.availableSecondDoseVaccine = availableSecondDoseVaccine;
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
	 * @return the availableCapacity
	 */
	public String getAvailableCapacity() {
		return availableCapacity;
	}

	/**
	 * @param availableCapacity the availableCapacity to set
	 */
	public void setAvailableCapacity(String availableCapacity) {
		this.availableCapacity = availableCapacity;
	}

	/**
	 * @return the vaccineName
	 */
	public String getVaccineName() {
		return vaccineName;
	}

	/**
	 * @param vaccineName the vaccineName to set
	 */
	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

}
