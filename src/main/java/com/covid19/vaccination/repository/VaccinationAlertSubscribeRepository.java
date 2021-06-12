/**
 * 
 */
package com.covid19.vaccination.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.covid19.vaccination.model.VaccinationAlertSubscribe;

/**
 * @author ab68478
 *
 */
@Repository
public class VaccinationAlertSubscribeRepository {

	@Autowired
	public MongoTemplate mongoTemplate;

	public void saveVaccinationAlertSubscriberInfo(VaccinationAlertSubscribe vaccinationAlertSubscribe) {
		mongoTemplate.save(vaccinationAlertSubscribe);
	}

	public List<VaccinationAlertSubscribe> getVaccinationAlertSubscriberInfo() {

		List<VaccinationAlertSubscribe> list = mongoTemplate.findAll(VaccinationAlertSubscribe.class);
		return list;
	}

	public List<String> getVaccinationAlertSubscriberDistricts() {

		List<String> list = mongoTemplate.findDistinct("district", VaccinationAlertSubscribe.class, String.class);

		return list;
	}

}
