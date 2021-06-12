/**
 * 
 */
package com.covid19.vaccination;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.covid19.vaccination.service.StateService;

/**
 * @author ab68478
 *
 */
@SpringBootTest
public class StateServiceTest {
	
	@Autowired
	private StateService stateService;
	
	@Test
	void contextLoads() {
	}
	
}
