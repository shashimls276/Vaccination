/**
 * 
 */
package com.covid19.vaccination.service;

import java.text.SimpleDateFormat;
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
import com.covid19.vaccination.repository.VaccinationInfoRepository;
import com.covid19.vaccination.util.CommonsUtil;
import com.covid19.vaccination.util.VaccinationAppConstants;

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

	public void savevaccinationInfo(String districtId) {

		HttpEntity<String> entity = CommonsUtil.getHttpEntity();

		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
		StringBuilder sb = new StringBuilder();
		sb.append(VaccinationAppConstants.vaccineSearchUrlByDistrict).append("district_id=").append(districtId)
				.append("&date=").append("27/06/2021");
		System.out.println(sb.toString());

		ResponseEntity<VaccinationInfo> response = restTemplate.exchange(sb.toString(), HttpMethod.GET, entity,
				VaccinationInfo.class);
		if (null != response.getBody()) {
			VaccinationInfo vaccinationInfo = response.getBody();
			vaccinationInfo.setDistrict(districtId);
			vaccinationInfoRepository.saveVaccinationInfo(vaccinationInfo);
		}

	}

	public List<VaccineInfoVo> getVaccinationInfoByDistrictId(String districtId) {

		VaccinationInfo vaccinationInfo = vaccinationInfoRepository.getVaccinationInfo(districtId);

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
						{
							vaccineInfoVo.setAvailableFirstDoseVaccine(sessionsVo.getAvailable_capacity_dose1());
							vaccineInfoVo.setAvailableSecondDoseVaccine(sessionsVo.getAvailable_capacity_dose2());
							vaccineInfoVo.setAgeLimit(sessionsVo.getMin_age_limit());
						}

						vaccineInfo.add(vaccineInfoVo);
					}
			}
		return vaccineInfo;

	}

}
