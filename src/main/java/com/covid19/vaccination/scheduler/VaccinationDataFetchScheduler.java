/**
 * 
 */
package com.covid19.vaccination.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.covid19.vaccination.service.VaccinationAlertSubscribeService;
import com.covid19.vaccination.service.VaccinationService;

/**
 * @author ab68478
 *
 */
@Configuration
@EnableScheduling
public class VaccinationDataFetchScheduler {

	@Autowired
	private VaccinationService vaccinationService;

	@Autowired
	private VaccinationAlertSubscribeService vaccinationAlertSubscribeService;

	@Scheduled(fixedDelay = 60000)
	public void scheduleFixedDelayTask() {
		System.out.println("Fixed delay task - " + System.currentTimeMillis() / 60000);

		List<String> vacccinAlertSubscribers = vaccinationAlertSubscribeService
				.getVaccinationAlertSubscriberDistricts();

		for (String district : vacccinAlertSubscribers) {
			vaccinationService.savevaccinationInfo(district);
		}

	}

}
