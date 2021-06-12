/**
 * 
 */
package com.covid19.vaccination.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covid19.vaccination.model.State;
import com.covid19.vaccination.service.StateService;

/**
 * @author ab68478
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/vaccinationInfo")
public class StateListController {
	
	@Autowired
	private StateService stateService;
	
	@RequestMapping(value = "/states")
	public List<State> getStateList(){
		List<State> states = stateService.getStates();
		return states;
	}

}
