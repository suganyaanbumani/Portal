package com.ntxs.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ntxs.Application;
import com.ntxs.model.Account;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class TestAccountDao {
	
	@Autowired
	public AccountDao accountDao;
	

	@Before
	public void setUp() {
	}
	
	@Test
	public void testSearchAccounts() {
		List<Account> accounts = accountDao.searchAccounts("James D Howard","DAVID M JOHNSON");
		Assert.assertSame("Account Size", 2, accounts.size());
	}
	
	
		
	
}
