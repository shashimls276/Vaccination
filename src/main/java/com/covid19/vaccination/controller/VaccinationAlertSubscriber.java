/**
 * 
 */
package com.covid19.vaccination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.covid19.vaccination.model.VaccinationAlertSubscribe;
import com.covid19.vaccination.service.VaccinationAlertSubscribeService;
import com.covid19.vaccination.vo.StatusMessageVo;

/**
 * @author ab68478
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/vaccinationInfo")
public class VaccinationAlertSubscriber {

	@Autowired
	private VaccinationAlertSubscribeService VaccinationAlertSubscribeService;

	@RequestMapping(value = "/subscribe", method = RequestMethod.POST)
	public StatusMessageVo vaccinationAlertSubscribe(@RequestBody VaccinationAlertSubscribe subscriber) {
		StatusMessageVo statusMessageVo = new StatusMessageVo();
		try {
			VaccinationAlertSubscribeService.saveVaccinationAlertSubscriberInfo(subscriber);
			statusMessageVo.setStatusMessage("Successfully Subscribed !!");
		} catch (Exception e) {
			e.getSuppressed();
			statusMessageVo.setStatusMessage("Not Subscribed..Please Try Later.");
		}
		return statusMessageVo;
	}

}
