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

import com.covid19.vaccination.model.State;
import com.covid19.vaccination.model.StateListInfo;
import com.covid19.vaccination.util.CommonsUtil;

/**
 * @author ab68478
 *
 */
@Service
public class StateService {

	@Autowired
	private RestTemplate restTemplate;

	String vaccinationStateListUrl = "https://cdn-api.co-vin.in/api/v2/admin/location/states";

	public List<State> getStates() {
		HttpEntity<String> entity = CommonsUtil.getHttpEntity();

		ResponseEntity<StateListInfo> stateList = restTemplate.exchange(vaccinationStateListUrl, HttpMethod.GET, entity,
				StateListInfo.class);
		StateListInfo statesListInfo = stateList.getBody();
		return statesListInfo.getStates();

	}

}
