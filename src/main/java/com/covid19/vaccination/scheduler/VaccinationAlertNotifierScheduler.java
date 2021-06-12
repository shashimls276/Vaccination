/**
 * 
 */
package com.covid19.vaccination.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.covid19.vaccination.model.VaccinationAlertSubscribe;
import com.covid19.vaccination.model.VaccineInfoVo;
import com.covid19.vaccination.service.MailSenderService;
import com.covid19.vaccination.service.VaccinationAlertSubscribeService;
import com.covid19.vaccination.service.VaccinationService;
import com.covid19.vaccination.util.CommonsUtil;

/**
 * @author ab68478
 *
 */
@Configuration
@EnableScheduling
public class VaccinationAlertNotifierScheduler {

	@Autowired
	private VaccinationService vaccinationService;

	@Autowired
	private MailSenderService mailSenderService;


	@Autowired
	private VaccinationAlertSubscribeService vaccinationAlertSubscribeService;

	String subject = "Vaccination Alert";

	@Scheduled(fixedDelay = 40000)
	public void vaccinationEmailNotifier() {
		List<VaccinationAlertSubscribe> vacccinAlertSubscribers = vaccinationAlertSubscribeService
				.getVaccinationAlertSubscriberInfo();
		for (VaccinationAlertSubscribe vaccinationAlertSubscribe : vacccinAlertSubscribers) {
			List<VaccineInfoVo> vaccineInfoVos = vaccinationService
					.getVaccinationInfoByDistrictId(vaccinationAlertSubscribe.getDistrict());
			
			notifySubscribers(vaccineInfoVos, vaccinationAlertSubscribe);
		}

	}

	private void notifySubscribers(List<VaccineInfoVo> vaccineInfoVos, VaccinationAlertSubscribe vaccinationAlertSubscribe) {

		for (VaccineInfoVo vaccineInfoVo : vaccineInfoVos) {
			int ageLimit=0;
			if(!CommonsUtil.isBlank(vaccineInfoVo.getAgeLimit()))
				ageLimit = Integer.parseInt(vaccineInfoVo.getAgeLimit());
			
			if((vaccinationAlertSubscribe.isUnder45() && ageLimit < 45) || (vaccinationAlertSubscribe.isAbove45() && ageLimit >= 45)) {
				
				if(vaccinationAlertSubscribe.isDose1Subscription()) {
					notifyDose1Availability(vaccineInfoVo, vaccinationAlertSubscribe.getEmail());
				}
				if(vaccinationAlertSubscribe.isDose2Subscription()) {
					notifyDose2Availability(vaccineInfoVo, vaccinationAlertSubscribe.getEmail());
				}
			}
		}
	}
	
	private void notifyDose1Availability(VaccineInfoVo vaccineInfoVo,String subscriberAddress) {
		
		if (!CommonsUtil.isBlank(vaccineInfoVo.getAvailableFirstDoseVaccine())) {
			if (Integer.parseInt(vaccineInfoVo.getAvailableFirstDoseVaccine()) > 0 ) {
				String message = "Pincode : " + vaccineInfoVo.getPincode() + "  Center : "
						+ vaccineInfoVo.getVaccinationCenter() + " Age Limit : "+vaccineInfoVo.getAgeLimit() +" " + " Dose 1 : "
						+ vaccineInfoVo.getAvailableFirstDoseVaccine() + " " + " Dose 2 : "
						+ vaccineInfoVo.getAvailableSecondDoseVaccine()+" Date : "+vaccineInfoVo.getDate();
				mailSenderService.sendmail(message, subject, subscriberAddress);
			}
		}
	}
	
	private void notifyDose2Availability(VaccineInfoVo vaccineInfoVo,String subscriberAddress) {
		if (!CommonsUtil.isBlank(vaccineInfoVo.getAvailableSecondDoseVaccine())) {
			if (Integer.parseInt(vaccineInfoVo.getAvailableSecondDoseVaccine()) > 0) {
				String message = "Pincode : " + vaccineInfoVo.getPincode() + "  Center : "
						+ vaccineInfoVo.getVaccinationCenter() + " Age Limit : "+vaccineInfoVo.getAgeLimit() +" " + " Dose 1 : "
						+ vaccineInfoVo.getAvailableFirstDoseVaccine() + " " + " Dose 2 : "
						+ vaccineInfoVo.getAvailableSecondDoseVaccine()+"  Date : "+vaccineInfoVo.getDate();
				mailSenderService.sendmail(message, subject, subscriberAddress);
			}
		}
	}
	
}
