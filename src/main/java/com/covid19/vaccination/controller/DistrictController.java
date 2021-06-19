/**
 * 
 */
package com.covid19.vaccination.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.covid19.vaccination.model.District;
import com.covid19.vaccination.model.State;
import com.covid19.vaccination.service.DistrictService;

/**
 * @author ab68478
 *
 */
@RestController
@RequestMapping(value = "/vaccinationInfo")
@CrossOrigin
public class DistrictController {

	@Autowired
	private DistrictService districtService;

	@RequestMapping(value = "/districts", method = RequestMethod.POST)
	public List<District> getDistrictList(@RequestBody State state) {
		System.out.println("state.getState_id() :: "+state.getState_id());
		return districtService.getDistricts(""+state.getState_id());

	}
	
}
