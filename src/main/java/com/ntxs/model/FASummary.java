package com.ntxs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name = "FASummary.fetchFASummary", query = "from FASummary Where advisorName=?1")})
@Entity
@Table(name = "advisor_summary_view")
public class FASummary {

	@Id
	@Column(name="advisor_name")
	private String advisorName;
	
	private int models;
	
	private int households;
	
	private int managers;
	
	private int accounts;

	public FASummary() {
		
	}
	
	public FASummary(String advisorName, int models, int households, int managers, int accounts) {
		super();
		this.advisorName = advisorName;
		this.models = models;
		this.households = households;
		this.managers = managers;
		this.accounts = accounts;
	}

	public String getAdvisorName() {
		return advisorName;
	}

	public void setAdvisorName(String advisorName) {
		this.advisorName = advisorName;
	}

	public int getModels() {
		return models;
	}

	public void setModels(int models) {
		this.models = models;
	}

	public int getHouseholds() {
		return households;
	}

	public void setHouseholds(int households) {
		this.households = households;
	}

	public int getManagers() {
		return managers;
	}

	public void setManagers(int managers) {
		this.managers = managers;
	}

	public int getAccounts() {
		return accounts;
	}

	public void setAccounts(int accounts) {
		this.accounts = accounts;
	}
	
	
}
