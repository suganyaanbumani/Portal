package com.ntxs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ntxs.dao.FADetailsDao;
import com.ntxs.model.FASummary;
import com.ntxs.service.FADetailsService;

@Component("faDetailsService")
@Transactional
public class FADetailsServiceImpl implements FADetailsService {

	public FADetailsDao faDetailsDao;
	
	@Autowired
	public FADetailsServiceImpl(FADetailsDao faDetailsDao) {
		this.faDetailsDao = faDetailsDao;
	}
	
	public FASummary getFASummary(String faName) {
		FASummary faSummary = faDetailsDao.fetchFASummary(faName);
		if(faSummary==null) {
			throw new RuntimeException("FA does not have any account details");
		}
		return faSummary;
	}
}
