/**
 * 
 */
package com.covid19.vaccination.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.covid19.vaccination.model.VaccinationInfo;
import com.covid19.vaccination.model.Sessions;
import com.covid19.vaccination.model.VaccinationCenters;
import com.covid19.vaccination.model.VaccineInfoVo;
import com.covid19.vaccination.service.VaccinationService;

/**
 * @author ab68478
 *
 */
@RestController
@RequestMapping("/vaccinationInfo")
public class VaccinationInfoController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private VaccinationService vaccinationService;

	String vaccineSearchUrlByPincode = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?";
	String vaccineSearchUrlByDistrict = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict?district_id=294&date=26-05-2021";
	

	@RequestMapping(value = "/district", method = RequestMethod.GET)
	public List<VaccineInfoVo> getVaccinationInfo() {

		List<VaccineInfoVo> vaccinationInfo= vaccinationService.getVaccinationInfoAbove45();

		return vaccinationInfo;
	}

	@RequestMapping(value = "/pincode/{value}", method = RequestMethod.GET)
	public List<VaccineInfoVo> getVaccinacionInfoByPincode(@PathVariable("value")String pincode) {
		
		HttpEntity<String> entity = getHttpEntity();
		ResponseEntity<VaccinationInfo> response = restTemplate.exchange(vaccineSearchUrlByDistrict, HttpMethod.GET, entity,
				VaccinationInfo.class);
		VaccinationInfo covidInfoVo = response.getBody();
		
		List<VaccineInfoVo> vaccineInfo = new ArrayList<VaccineInfoVo>();
		
		if(covidInfoVo != null)
		for(VaccinationCenters vaccinationCentersVo : covidInfoVo.getCenters()) {
			VaccineInfoVo vaccineInfoVo = new VaccineInfoVo();
			
			vaccineInfoVo.setVaccinationCenter(vaccinationCentersVo.getName());
			vaccineInfoVo.setPincode(vaccinationCentersVo.getPincode());
			
			if(null != vaccinationCentersVo.getSessions())
				for(Sessions sessionsVo : vaccinationCentersVo.getSessions()) {
					if(Integer.parseInt(sessionsVo.getMin_age_limit()) >=18 && Integer.parseInt(sessionsVo.getMin_age_limit()) < 45) {
						vaccineInfoVo.setAvailableFirstDoseVaccine(sessionsVo.getAvailable_capacity_dose1());
						vaccineInfoVo.setAvailableSecondDoseVaccine(sessionsVo.getAvailable_capacity_dose2());
						vaccineInfoVo.setAgeLimit(sessionsVo.getMin_age_limit());
					}
					
					vaccineInfo.add(vaccineInfoVo);
				}
		}
		

		return vaccineInfo;

	}
	
	public static void main(String[] args) {
		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
		System.out.println(sf.format(new Date()));
	}
	
	@RequestMapping(value = "/pincode/firstdose/{value}", method = RequestMethod.GET)
	public List<VaccineInfoVo> getFirstDoseVaccinacionInfoByPincode(@PathVariable("value")String pincode) {
		
		HttpEntity<String> entity = getHttpEntity();
		
		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(vaccineSearchUrlByPincode).append("pincode=").append(pincode).append("&date=").append(sf.format(new Date()));
		
		System.out.println(vaccineSearchUrlByPincode);
		
		ResponseEntity<VaccinationInfo> response = restTemplate.exchange(sb.toString(), HttpMethod.GET, entity,
				VaccinationInfo.class);
		VaccinationInfo covidInfoVo = response.getBody();
		
		List<VaccineInfoVo> vaccineInfo = new ArrayList<VaccineInfoVo>();
		
		if(covidInfoVo != null && null != covidInfoVo.getCenters())
		for(VaccinationCenters vaccinationCentersVo : covidInfoVo.getCenters()) {
			VaccineInfoVo vaccineInfoVo = new VaccineInfoVo();
			
			vaccineInfoVo.setVaccinationCenter(vaccinationCentersVo.getName());
			vaccineInfoVo.setPincode(vaccinationCentersVo.getPincode());
			
			if(null != vaccinationCentersVo.getSessions())
				for(Sessions sessionsVo : vaccinationCentersVo.getSessions()) {
					if(Integer.parseInt(sessionsVo.getMin_age_limit()) >=18 && Integer.parseInt(sessionsVo.getMin_age_limit()) < 45) {
						vaccineInfoVo.setAvailableFirstDoseVaccine(sessionsVo.getAvailable_capacity_dose1());
						vaccineInfoVo.setAvailableSecondDoseVaccine(sessionsVo.getAvailable_capacity_dose2());
						vaccineInfoVo.setAgeLimit(sessionsVo.getMin_age_limit());
					}
					
					vaccineInfo.add(vaccineInfoVo);
				}
		}
		

		return vaccineInfo;

	}


	private HttpEntity<String> getHttpEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		return entity;
	}

}
