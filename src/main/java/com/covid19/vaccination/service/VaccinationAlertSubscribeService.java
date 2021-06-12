/**
 * 
 */
package com.covid19.vaccination.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid19.vaccination.model.VaccinationAlertSubscribe;
import com.covid19.vaccination.repository.VaccinationAlertSubscribeRepository;

/**
 * @author ab68478
 *
 */
@Service
public class VaccinationAlertSubscribeService {
	
	@Autowired
	private VaccinationAlertSubscribeRepository vaccinationAlertSubscribeRepository;
	
	public void saveVaccinationAlertSubscriberInfo(VaccinationAlertSubscribe vaccinationAlertSubscribe) {
		vaccinationAlertSubscribeRepository.saveVaccinationAlertSubscriberInfo(vaccinationAlertSubscribe);
	} 
	
	public List<VaccinationAlertSubscribe> getVaccinationAlertSubscriberInfo() {
		return vaccinationAlertSubscribeRepository.getVaccinationAlertSubscriberInfo();
	}
	
	public List<String> getVaccinationAlertSubscriberDistricts() {
		return vaccinationAlertSubscribeRepository.getVaccinationAlertSubscriberDistricts();
	}
	
}
