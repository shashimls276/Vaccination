/**
 * 
 */
package com.covid19.vaccination.service;

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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.covid19.vaccination.model.Sessions;
import com.covid19.vaccination.model.VaccinationCenters;
import com.covid19.vaccination.model.VaccinationInfo;
import com.covid19.vaccination.model.VaccineInfoVo;
import com.covid19.vaccination.repository.VaccinationInfoRepository;

/**
 * @author ab68478
 *
 */
@Service
public class VaccinationService {

	@Autowired
	private VaccinationInfoRepository vaccinationInfoRepository;

	@Autowired
	private RestTemplate restTemplate;

	String vaccineSearchUrlByPincode = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?";
	String vaccineSearchUrlByDistrict = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict?";

	public void savevaccinationInfo(int districtId) {

		HttpEntity<String> entity = getHttpEntity();

		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
		StringBuilder sb = new StringBuilder();
		sb.append(vaccineSearchUrlByDistrict).append("district_id=").append(districtId).append("&date=")
				.append("04/06/2021");
		System.out.println(sb.toString());

		ResponseEntity<VaccinationInfo> response = restTemplate.exchange(sb.toString(), HttpMethod.GET,
				entity, VaccinationInfo.class);
		if (null != response.getBody()) {
			vaccinationInfoRepository.saveVaccinationInfo(response.getBody());
		}

	}

	public List<VaccineInfoVo> getVaccinationInfoUnder45() {

		VaccinationInfo vaccinationInfo = vaccinationInfoRepository.getVaccinationInfo();

		List<VaccineInfoVo> vaccineInfo = new ArrayList<VaccineInfoVo>();

		if (vaccinationInfo != null)
			for (VaccinationCenters vaccinationCentersVo : vaccinationInfo.getCenters()) {
				VaccineInfoVo vaccineInfoVo = new VaccineInfoVo();
				
				vaccineInfoVo.setId(vaccinationInfo.getId());
				vaccineInfoVo.setVaccinationCenter(vaccinationCentersVo.getName());
				vaccineInfoVo.setPincode(vaccinationCentersVo.getPincode());
				vaccineInfoVo.setDate(vaccinationInfo.getDate());

				if (null != vaccinationCentersVo.getSessions())
					for (Sessions sessionsVo : vaccinationCentersVo.getSessions()) {
						if (Integer.parseInt(sessionsVo.getMin_age_limit()) >= 18
								&& Integer.parseInt(sessionsVo.getMin_age_limit()) < 45) {
							vaccineInfoVo.setAvailableFirstDoseVaccine(sessionsVo.getAvailable_capacity_dose1());
							vaccineInfoVo.setAvailableSecondDoseVaccine(sessionsVo.getAvailable_capacity_dose2());
							vaccineInfoVo.setAgeLimit(sessionsVo.getMin_age_limit());
						}

						vaccineInfo.add(vaccineInfoVo);
					}
			}
		return vaccineInfo;

	}
	
	public List<VaccineInfoVo> getVaccinationInfoAbove45() {

		VaccinationInfo vaccinationInfo = vaccinationInfoRepository.getVaccinationInfo();

		List<VaccineInfoVo> vaccineInfo = new ArrayList<VaccineInfoVo>();

		if (vaccinationInfo != null)
			for (VaccinationCenters vaccinationCentersVo : vaccinationInfo.getCenters()) {
				VaccineInfoVo vaccineInfoVo = new VaccineInfoVo();

				vaccineInfoVo.setVaccinationCenter(vaccinationCentersVo.getName());
				vaccineInfoVo.setPincode(vaccinationCentersVo.getPincode());

				if (null != vaccinationCentersVo.getSessions())
					for (Sessions sessionsVo : vaccinationCentersVo.getSessions()) {
						if (Integer.parseInt(sessionsVo.getMin_age_limit()) >= 45) {
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
