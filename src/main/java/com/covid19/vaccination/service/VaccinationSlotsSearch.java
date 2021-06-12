/**
 * 
 */
package com.covid19.vaccination.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.covid19.vaccination.model.Sessions;
import com.covid19.vaccination.model.VaccinationCenters;
import com.covid19.vaccination.model.VaccinationInfo;
import com.covid19.vaccination.model.VaccineInfoVo;
import com.covid19.vaccination.util.CommonsUtil;
import com.covid19.vaccination.util.VaccinationAppConstants;
import com.covid19.vaccination.vo.VaccinationSlotSearchVo;

/**
 * @author ab68478
 *
 */
@Service
public class VaccinationSlotsSearch {

	@Autowired
	private RestTemplate restTemplate;

	public List<VaccineInfoVo> searchVaccinationSlots(VaccinationSlotSearchVo vaccinationSlotSearchVo) {

		List<VaccineInfoVo> vaccineInfo = new ArrayList<VaccineInfoVo>();

		if (!CommonsUtil.isBlank(vaccinationSlotSearchVo.getPincode())) {
			StringBuilder sb = new StringBuilder();
			sb.append(VaccinationAppConstants.vaccineSearchUrlByPincode).append("pincode=")
					.append(vaccinationSlotSearchVo.getPincode()).append("&date=").append("13/06/2021");

			HttpEntity<String> entity = CommonsUtil.getHttpEntity();
			ResponseEntity<VaccinationInfo> response = restTemplate.exchange(sb.toString(), HttpMethod.GET, entity,
					VaccinationInfo.class);
			VaccinationInfo covidInfoVo = response.getBody();

			if (covidInfoVo != null)
				for (VaccinationCenters vaccinationCentersVo : covidInfoVo.getCenters()) {
					if (null != vaccinationCentersVo.getSessions())
						for (Sessions sessionsVo : vaccinationCentersVo.getSessions()) {
							VaccineInfoVo vaccineInfoVo = new VaccineInfoVo();

							vaccineInfoVo.setVaccinationCenter(vaccinationCentersVo.getName());
							vaccineInfoVo.setPincode(vaccinationCentersVo.getPincode());
							
							vaccineInfoVo.setAvailableFirstDoseVaccine(sessionsVo.getAvailable_capacity_dose1());
							vaccineInfoVo.setAvailableSecondDoseVaccine(sessionsVo.getAvailable_capacity_dose2());
							vaccineInfoVo.setAgeLimit(sessionsVo.getMin_age_limit());
							vaccineInfoVo.setAvailableCapacity(sessionsVo.getAvailable_capacity());
							vaccineInfoVo.setVaccineName(sessionsVo.getVaccine());
							vaccineInfoVo.setDate(sessionsVo.getDate());
							vaccineInfo.add(vaccineInfoVo);
						}
				}
		}

		return vaccineInfo;
	}

}
