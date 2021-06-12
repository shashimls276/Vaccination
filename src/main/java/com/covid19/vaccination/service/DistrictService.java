/**
 * 
 */
package com.covid19.vaccination.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.covid19.vaccination.model.District;
import com.covid19.vaccination.model.DistrictListInfo;
import com.covid19.vaccination.util.CommonsUtil;

/**
 * @author ab68478
 *
 */
@Service
public class DistrictService {

	@Autowired
	private RestTemplate restTemplate;

	String vaccinationDistrictListUrl = "https://cdn-api.co-vin.in/api/v2/admin/location/districts/";

	public List<District> getDistricts(String stateId) {
		HttpEntity<String> entity = CommonsUtil.getHttpEntity();

		ResponseEntity<DistrictListInfo> diResponseEntity = restTemplate.exchange(vaccinationDistrictListUrl+stateId,
				HttpMethod.GET, entity, DistrictListInfo.class);
		DistrictListInfo districtListInfo = diResponseEntity.getBody();
		return districtListInfo.getDistricts();

	}

}
