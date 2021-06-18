package com.covid19.vaccination.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ServiceDiscoveryController {

	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping("/service-instances/{applicationName}")
	public ServiceDiscoveryVo serviceInstancesByApplicationName(@PathVariable String applicationName) {
		List<ServiceInstance> serviceInstances = this.discoveryClient.getInstances(applicationName);
		ServiceDiscoveryVo serviceDiscoveryVo = new ServiceDiscoveryVo();
		
		for(ServiceInstance s : serviceInstances) {
			System.out.println("Name :: "+s);
			System.out.println(s.getUri());
			serviceDiscoveryVo.setUrl(s.getUri().toString());
		}
		return serviceDiscoveryVo;
		
	}
	
	@GetMapping("/testing")
	public String getString() {
		return "Hello Latest Image";
	}

}
