/**
 * 
 */
package com.covid19.vaccination.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @author ab68478
 *
 */
@Service
public class SubscriberService {
	
	public static List<String> dose1SubscriberList ;
	public static List<String> dose2SubscriberList ;
	
	static {
		dose1SubscriberList = new ArrayList<String>();
		dose1SubscriberList.add("vaccinationalerts@gmail.com");
		
		dose2SubscriberList = new ArrayList<String>();
		
	}
	
	public List<String> getDose1VaccineSubscribersList(){
		return dose1SubscriberList;
	}
	
	public List<String> getDose2VaccineSubscribersList(){
		return dose2SubscriberList;
	}
	
}
