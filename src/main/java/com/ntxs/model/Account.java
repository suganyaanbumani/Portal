package com.ntxs.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "advisor_account_summary_view")
public class Account {

	public static final NumberFormat MONEY_FORMAT = new DecimalFormat("$##,###,###");
	
	@Id
	private Long accountId;
	
	@Column(name="advisor_name")
	private String advisorName;
	
	@Column(name="Account_Number")
	private String accountNumber;
	
	@Column(name="Account_Title")
	private String accountTitle;
	
	@Column(name="Account_Name")
	private String accountName;
	
	@Column(name="Market_Value")
	private BigDecimal marketValue;
	
	@Column(name="YTD_Performance")
	private BigDecimal ytdPerformance;
	
	@Column(name="One_Year_Performance")
	private BigDecimal oneYearPerformance;
	
	@Column(name="Two_Year_Performance")
	private BigDecimal twoYearPerformance;
	
	@Column(name="Three_Year_Performance")
	private BigDecimal threeYearPerformance;
	
	@Column(name="Five_Year_Performance")
	private BigDecimal fiveYearPerformance;
	
	@Column(name="Ten_Year_Performance")
	private BigDecimal tenYearPerformance;
	
	@Column(name="Since_Inception_Performance")
	private BigDecimal sinceInceptionPerformance;
	
	@Column(name="LT_Realized_Gain")
	private BigDecimal LTRealizedGain;
	
	@Column(name="LT_Realized_Loss")
	private BigDecimal LTRealizedLoss;
	
	@Column(name="ST_Realized_Gain")
	private BigDecimal STRealizedGain;
	
	@Column(name="ST_Realized_Loss")
	private BigDecimal STRealizedLoss;
	
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public BigDecimal getMarketValue() {
		return marketValue;
	}
	
	public String getMarketValueString() {
		if (marketValue == null) {
	         return null;
	      }
	    return MONEY_FORMAT.format(marketValue);
	}

	public void setMarketValue(BigDecimal marketValue) {
		this.marketValue = marketValue;
	}

	public BigDecimal getYtdPerformance() {
		return ytdPerformance;
	}

	public void setYtdPerformance(BigDecimal ytdPerformance) {
		this.ytdPerformance = ytdPerformance;
	}

	public String getAdvisorName() {
		return advisorName;
	}

	public void setAdvisorName(String advisorName) {
		this.advisorName = advisorName;
	}

	public String getAccountTitle() {
		return accountTitle;
	}

	public void setAccountTitle(String accountTitle) {
		this.accountTitle = accountTitle;
	}

	public BigDecimal getOneYearPerformance() {
		return oneYearPerformance;
	}

	public void setOneYearPerformance(BigDecimal oneYearPerformance) {
		this.oneYearPerformance = oneYearPerformance;
	}

	public BigDecimal getTwoYearPerformance() {
		return twoYearPerformance;
	}

	public void setTwoYearPerformance(BigDecimal twoYearPerformance) {
		this.twoYearPerformance = twoYearPerformance;
	}

	public BigDecimal getThreeYearPerformance() {
		return threeYearPerformance;
	}

	public void setThreeYearPerformance(BigDecimal threeYearPerformance) {
		this.threeYearPerformance = threeYearPerformance;
	}

	public BigDecimal getFiveYearPerformance() {
		return fiveYearPerformance;
	}

	public void setFiveYearPerformance(BigDecimal fiveYearPerformance) {
		this.fiveYearPerformance = fiveYearPerformance;
	}

	public BigDecimal getTenYearPerformance() {
		return tenYearPerformance;
	}

	public void setTenYearPerformance(BigDecimal tenYearPerformance) {
		this.tenYearPerformance = tenYearPerformance;
	}

	public BigDecimal getSinceInceptionPerformance() {
		return sinceInceptionPerformance;
	}

	public void setSinceInceptionPerformance(BigDecimal sinceInceptionPerformance) {
		this.sinceInceptionPerformance = sinceInceptionPerformance;
	}

	public BigDecimal getLTRealizedGain() {
		return LTRealizedGain ==null?BigDecimal.ZERO:LTRealizedGain;
	}

	public void setLTRealizedGain(BigDecimal lTRealizedGain) {
		LTRealizedGain = lTRealizedGain;
	}

	public BigDecimal getLTRealizedLoss() {
		return LTRealizedLoss ==null?BigDecimal.ZERO:LTRealizedLoss;
	}

	public void setLTRealizedLoss(BigDecimal lTRealizedLoss) {
		LTRealizedLoss = lTRealizedLoss;
	}

	public BigDecimal getSTRealizedGain() {
		return STRealizedGain ==null?BigDecimal.ZERO:STRealizedGain;
	}

	public void setSTRealizedGain(BigDecimal sTRealizedGain) {
		STRealizedGain = sTRealizedGain;
	}

	public BigDecimal getSTRealizedLoss() {
		return STRealizedLoss ==null?BigDecimal.ZERO:STRealizedLoss;
	}

	public void setSTRealizedLoss(BigDecimal sTRealizedLoss) {
		STRealizedLoss = sTRealizedLoss;
	}

	public String getLTRealizedGainLoss() {
		BigDecimal ltgl= getLTRealizedGain().add(getLTRealizedLoss());
		if (ltgl == null) {
	         return null;
	     }
	    return MONEY_FORMAT.format(ltgl);
	}

	public String getSTRealizedGainLoss() {
		BigDecimal stgl=  getSTRealizedGain().add(getSTRealizedLoss());
		if (stgl == null) {
	         return null;
	     }
	    return MONEY_FORMAT.format(stgl);
	}

}
