package com.ntxs.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ntxs.dao.AccountDao;
import com.ntxs.dao.UserDao;
import com.ntxs.model.Account;
import com.ntxs.model.FASummary;
import com.ntxs.model.User;
import com.ntxs.security.SecurityUtils;
import com.ntxs.service.FADetailsService;
import com.ntxs.vo.FASummaryDetails;

@RestController
public class FADetailsController {
	
	private static final Logger logger = LoggerFactory.getLogger(FADetailsController.class);

	
	@Autowired	
	private FADetailsService faDetailsService;
	
	@Autowired	
	private AccountDao accountDao;
	
	@Autowired	
    private UserDao userDao;
	
	@CrossOrigin
	@RequestMapping("/getDashBoardDetails")
	@ResponseBody
	public FASummary getDashBoardDetails() {
		FASummary faSummary;
		User user = userDao.findByLogin(SecurityUtils.getCurrentLogin());
		faSummary = faDetailsService.getFASummary(user.getFullName());
		return faSummary;
	}
	
	@CrossOrigin
	@RequestMapping("/getSummaryDetails")
	@ResponseBody
	public FASummaryDetails getSummaryDetails() {
		User user = userDao.findByLogin(SecurityUtils.getCurrentLogin());
		List<Account> accounts = accountDao.findByAdvisorName(user.getFullName());
		FASummaryDetails faSummaryDetails = populateFASummaryDetails(accounts);
		return faSummaryDetails;
	}
	
	@RequestMapping(value = "/getSummaryDetails/{searchString}", method = RequestMethod.GET)
	@ResponseBody 
    public FASummaryDetails getSummaryDetails(@PathVariable String searchString) {
        logger.debug("get account details :: "+searchString);
        searchString = "%"+searchString+"%";
        User user = userDao.findByLogin(SecurityUtils.getCurrentLogin());
        List<Account> accounts =  accountDao.searchAccounts(user.getFullName(), searchString);
		FASummaryDetails faSummaryDetails = populateFASummaryDetails(accounts);
		return faSummaryDetails;
    }
	
	private FASummaryDetails populateFASummaryDetails(List<Account> accounts) {
		List<String> accountNames = new ArrayList<>();
		Map<String,BigDecimal> ytdPerformance = new HashMap<>();
		Map<String,BigDecimal> marketValues = new HashMap<>();
		Map<String,BigDecimal> oneYearPerformance = new HashMap<>();
		Map<String,BigDecimal> twoYearPerformance = new HashMap<>();
		Map<String,BigDecimal> threeYearPerformance = new HashMap<>();
		Map<String,BigDecimal> fiveYearPerformance = new HashMap<>();
		Map<String,BigDecimal> tenYearPerformance = new HashMap<>();
		Map<String,BigDecimal> sinceInceptionPerformance = new HashMap<>();
		Map<String,BigDecimal> LTRealizedGainLoss = new HashMap<>();
		Map<String,BigDecimal> STRealizedGainLoss = new HashMap<>();
		
		for(Account account: accounts) {
			accountNames.add(account.getAccountName());
			ytdPerformance.put(account.getAccountName(),account.getYtdPerformance());
			marketValues.put(account.getAccountName(),account.getMarketValue());
			oneYearPerformance.put(account.getAccountName(),account.getOneYearPerformance());
			twoYearPerformance.put(account.getAccountName(),account.getTwoYearPerformance());
			threeYearPerformance.put(account.getAccountName(),account.getThreeYearPerformance());
			fiveYearPerformance.put(account.getAccountName(),account.getFiveYearPerformance());
			tenYearPerformance.put(account.getAccountName(),account.getTenYearPerformance());
			sinceInceptionPerformance.put(account.getAccountName(),account.getSinceInceptionPerformance());
			LTRealizedGainLoss.put(account.getAccountName(),account.getLTRealizedGain().add(account.getLTRealizedLoss()));
			STRealizedGainLoss.put(account.getAccountName(),account.getSTRealizedGain().add(account.getSTRealizedLoss()));
		}
		
		FASummaryDetails faSummaryDetails = new FASummaryDetails(accounts, accountNames, ytdPerformance, marketValues, oneYearPerformance, 
				twoYearPerformance, threeYearPerformance, fiveYearPerformance, tenYearPerformance, sinceInceptionPerformance,
				LTRealizedGainLoss, STRealizedGainLoss);
		return faSummaryDetails;
	}
	
	
}
