/**
 * 
 */
package com.covid19.vaccination.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.covid19.vaccination.service.VaccinationService;

/**
 * @author ab68478
 *
 */
@Configuration
@EnableScheduling
public class VaccinationAbove45Scheduler {

	@Autowired
	private VaccinationService vaccinationService;

	// @Scheduled(fixedDelay = 60000)
	public void scheduleFixedDelayTask() {
		System.out.println("Fixed delay task - " + System.currentTimeMillis() / 60000);

		vaccinationService.savevaccinationInfo(294);

	}

}
