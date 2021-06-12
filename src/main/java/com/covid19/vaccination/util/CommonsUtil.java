/**
 * 
 */
package com.covid19.vaccination.util;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

/**
 * @author ab68478
 *
 */
@Component
public class CommonsUtil {

	public static boolean isBlank(String value) {
		return !(StringUtils.isNotBlank(value) && !value.trim().equalsIgnoreCase("null"));
	}
	
	public static HttpEntity<String> getHttpEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		return entity;
	}
	
}
