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

import com.covid19.vaccination.model.VaccineInfoVo;
import com.covid19.vaccination.service.VaccinationSlotsSearch;
import com.covid19.vaccination.vo.VaccinationSlotSearchVo;

/**
 * @author ab68478
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/vaccinationInfo")
public class VaccinationSlotSearchController {
	
	@Autowired
	private VaccinationSlotsSearch vaccinationSlotsSearch;
	
	@RequestMapping(value="/search",method = RequestMethod.POST)
	private List<VaccineInfoVo> searchVaccinationSlots(@RequestBody VaccinationSlotSearchVo searchVo) {
		return vaccinationSlotsSearch.searchVaccinationSlots(searchVo);
	}
	
}
