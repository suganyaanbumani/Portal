package com.ntxs.vo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ntxs.model.Account;

public class FASummaryDetails {

	private List<Account> accounts;
	
	private List<String> accountNames;
	
	private Map<String,BigDecimal> marketValues;
	
	private Map<String,BigDecimal> ytdPerformance; 
	
	private Map<String,BigDecimal> oneYearPerformance; 
	
	private Map<String,BigDecimal> twoYearPerformance;
	
	private Map<String,BigDecimal> threeYearPerformance; 
	
	private Map<String,BigDecimal> fiveYearPerformance; 
	
	private Map<String,BigDecimal> tenYearPerformance; 
	
	private Map<String,BigDecimal> sinceInceptionPerformance;
	
	private Map<String,BigDecimal> LTRealizedGainLoss;
	
	private Map<String,BigDecimal> STRealizedGainLoss;
	
	public FASummaryDetails() {
		
	}
	
	public FASummaryDetails(List<Account> accounts, List<String> accountNames,
			Map<String,BigDecimal> ytdPerformance, Map<String,BigDecimal> marketValues,
			Map<String,BigDecimal> oneYearPerformance, Map<String,BigDecimal> twoYearPerformance,
			Map<String,BigDecimal> threeYearPerformance, Map<String,BigDecimal> fiveYearPerformance,
			Map<String,BigDecimal> tenYearPerformance, Map<String,BigDecimal> sinceInceptionPerformance,
			Map<String,BigDecimal> LTRealizedGainLoss, Map<String,BigDecimal> STRealizedGainLoss) {
		this.accounts = accounts;
		this.accountNames = accountNames;
		this.ytdPerformance = ytdPerformance;
		this.marketValues = marketValues;
		this.oneYearPerformance = oneYearPerformance;
		this.twoYearPerformance = twoYearPerformance;
		this.threeYearPerformance = threeYearPerformance;
		this.fiveYearPerformance = fiveYearPerformance;
		this.tenYearPerformance = tenYearPerformance;
		this.sinceInceptionPerformance = sinceInceptionPerformance;
		this.LTRealizedGainLoss = LTRealizedGainLoss;
		this.STRealizedGainLoss = STRealizedGainLoss;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<String> getAccountNames() {
		return accountNames;
	}

	public void setAccountNames(List<String> accountNames) {
		this.accountNames = accountNames;
	}

	public Map<String, BigDecimal> getYtdPerformance() {
		return ytdPerformance;
	}

	public void setYtdPerformance(Map<String, BigDecimal> ytdPerformance) {
		this.ytdPerformance = ytdPerformance;
	}

	public Map<String,BigDecimal> getMarketValues() {
		return marketValues;
	}

	public void setMarketValues(Map<String,BigDecimal> marketValues) {
		this.marketValues = marketValues;
	}

	public Map<String, BigDecimal> getOneYearPerformance() {
		return oneYearPerformance;
	}

	public void setOneYearPerformance(Map<String, BigDecimal> oneYearPerformance) {
		this.oneYearPerformance = oneYearPerformance;
	}

	public Map<String, BigDecimal> getTwoYearPerformance() {
		return twoYearPerformance;
	}

	public void setTwoYearPerformance(Map<String, BigDecimal> twoYearPerformance) {
		this.twoYearPerformance = twoYearPerformance;
	}

	public Map<String, BigDecimal> getThreeYearPerformance() {
		return threeYearPerformance;
	}

	public void setThreeYearPerformance(Map<String, BigDecimal> threeYearPerformance) {
		this.threeYearPerformance = threeYearPerformance;
	}

	public Map<String, BigDecimal> getFiveYearPerformance() {
		return fiveYearPerformance;
	}

	public void setFiveYearPerformance(Map<String, BigDecimal> fiveYearPerformance) {
		this.fiveYearPerformance = fiveYearPerformance;
	}

	public Map<String, BigDecimal> getTenYearPerformance() {
		return tenYearPerformance;
	}

	public void setTenYearPerformance(Map<String, BigDecimal> tenYearPerformance) {
		this.tenYearPerformance = tenYearPerformance;
	}

	public Map<String, BigDecimal> getSinceInceptionPerformance() {
		return sinceInceptionPerformance;
	}

	public void setSinceInceptionPerformance(
			Map<String, BigDecimal> sinceInceptionPerformance) {
		this.sinceInceptionPerformance = sinceInceptionPerformance;
	}

	public Map<String, BigDecimal> getLTRealizedGainLoss() {
		return LTRealizedGainLoss;
	}

	public void setLTRealizedGainLoss(Map<String, BigDecimal> lTRealizedGainLoss) {
		LTRealizedGainLoss = lTRealizedGainLoss;
	}

	public Map<String, BigDecimal> getSTRealizedGainLoss() {
		return STRealizedGainLoss;
	}

	public void setSTRealizedGainLoss(Map<String, BigDecimal> sTRealizedGainLoss) {
		STRealizedGainLoss = sTRealizedGainLoss;
	}
	
}
