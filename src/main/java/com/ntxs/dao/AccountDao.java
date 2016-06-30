package com.ntxs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ntxs.model.Account;

public interface AccountDao  extends JpaRepository<Account, Long>  {

	List<Account> findByAdvisorName(String advisorName);
	
	List<Account> findByAdvisorNameAndAccountNameContainingOrAccountNumberContaining(String advisorName, 
			String searchString, String searchString1);
	
	@Query("select acc from Account acc where acc.advisorName = :advisorName and "
			+ "(acc.accountName like %:searchString% or acc.accountNumber like %:searchString%"
			+ " or acc.accountTitle like %:searchString%)")
	List<Account> searchAccounts(@Param("advisorName") String advisorName,
	                                 @Param("searchString") String searchString);
}
