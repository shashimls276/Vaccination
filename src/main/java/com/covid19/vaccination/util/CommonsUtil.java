/**
 * 
 */
package com.covid19.vaccination.util;

import org.apache.commons.lang3.StringUtils;
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
	
}
