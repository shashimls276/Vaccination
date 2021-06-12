/**
 * 
 */
package com.covid19.vaccination.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covid19.vaccination.model.District;
import com.covid19.vaccination.service.DistrictService;

/**
 * @author ab68478
 *
 */
@RestController
@RequestMapping(value="/vaccinationInfo")
@CrossOrigin
public class DistrictController {

	@Autowired
	private DistrictService districtService;

	@RequestMapping(value = "/districts/{stateId}")
	public List<District> getDistrictList(@PathVariable("stateId") String stateId) {
		return districtService.getDistricts(stateId);

	}

}
