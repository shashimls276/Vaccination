/**
 * 
 */
package com.covid19.vaccination.repository;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.covid19.vaccination.model.VaccinationInfo;

/**
 * @author ab68478
 *
 */
@Repository
public class VaccinationInfoRepository {

	@Autowired
	public MongoTemplate mongoTemplate;

	public void saveVaccinationInfo(VaccinationInfo vaccinationInfo) {
		
		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		System.out.println(sf.format(new Date()));
		
		vaccinationInfo.setDate(sf.format(new Date()));
		mongoTemplate.save(vaccinationInfo);
		
	}

	public VaccinationInfo getVaccinationInfo(String districtId) {
		Criteria criteria = new Criteria("district").is(districtId);
		Query query = new Query();
		query.addCriteria(criteria);
		query.with(Sort.by(Sort.Direction.DESC,"_id"));
		query.limit(1);
		return mongoTemplate.findOne(query, VaccinationInfo.class);
	}

}
