package com.ntxs.dao;

import org.springframework.data.repository.Repository;

import com.ntxs.model.FASummary;

public interface FADetailsDao extends Repository<FASummary, String> {

	public FASummary fetchFASummary(String name);
}
