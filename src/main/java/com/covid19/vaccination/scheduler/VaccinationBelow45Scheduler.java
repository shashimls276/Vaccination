/**
 * 
 */
package com.covid19.vaccination.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.covid19.vaccination.model.VaccineInfoVo;
import com.covid19.vaccination.service.MailSenderService;
import com.covid19.vaccination.service.SubscriberService;
import com.covid19.vaccination.service.VaccinationService;
import com.covid19.vaccination.util.CommonsUtil;

/**
 * @author ab68478
 *
 */
@Configuration
@EnableScheduling
public class VaccinationBelow45Scheduler {
	@Autowired
	private VaccinationService vaccinationService;

	@Autowired
	private MailSenderService mailSenderService;

	@Autowired
	private SubscriberService subscriberService;

	@Scheduled(fixedDelay = 60000)
	public void scheduleFixedDelayTask() {
		System.out.println("Fixed delay task - " + System.currentTimeMillis() / 60000);

		vaccinationService.savevaccinationInfo(294);

	}

	@Scheduled(fixedDelay = 40000)
	public void firstDoseVaccinationEmailNotifier() {
		String subject = "Vaccination Alert";
		List<VaccineInfoVo> vaccineInfoVos = vaccinationService.getVaccinationInfoUnder45();
		
		
		for (VaccineInfoVo vaccineInfoVo : vaccineInfoVos) {
			if (!CommonsUtil.isBlank(vaccineInfoVo.getAvailableFirstDoseVaccine())) {
				if (Integer.parseInt(vaccineInfoVo.getAvailableFirstDoseVaccine()) > 0) {
					String message = "Pincode : " + vaccineInfoVo.getPincode() + "  Center : "
							+ vaccineInfoVo.getVaccinationCenter() + "/n" + " Dose 1 : "
							+ vaccineInfoVo.getAvailableFirstDoseVaccine() + " /n" + " Dose 2 : "
							+ vaccineInfoVo.getAvailableSecondDoseVaccine();
					System.out.println(message);
					System.out.println("date :: "+vaccineInfoVo.getDate());
					System.out.println("id:"+vaccineInfoVo.getId());
					for (String address : subscriberService.getDose1VaccineSubscribersList()) {
						mailSenderService.sendmail(message, subject,address);
					}
				}
			}

		}
	}

	@Scheduled(fixedDelay = 30000000)
	public void secondDoseVaccinationEmailNotifier() {
		String subject = "Vaccination Alert";
		List<VaccineInfoVo> vaccineInfoVos = vaccinationService.getVaccinationInfoUnder45();

		for (VaccineInfoVo vaccineInfoVo : vaccineInfoVos) {

			if (!CommonsUtil.isBlank(vaccineInfoVo.getAvailableSecondDoseVaccine())) {
				if (Integer.parseInt(vaccineInfoVo.getAvailableSecondDoseVaccine()) > 0) {
					String message = "Pincode : " + vaccineInfoVo.getPincode() + "  Center : "
							+ vaccineInfoVo.getVaccinationCenter() + "/n" + " Dose 1 : "
							+ vaccineInfoVo.getAvailableFirstDoseVaccine() + " /n" + " Dose 2 : "
							+ vaccineInfoVo.getAvailableSecondDoseVaccine();
					System.out.println(message);
					for (String address : subscriberService.getDose2VaccineSubscribersList()) {
						mailSenderService.sendmail(message, subject,address);
					}
				}
			}

		}
	}
}
