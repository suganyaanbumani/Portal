package com.ntxs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ntxs.model.User;

public interface UserDao  extends JpaRepository<User, Long>  {

	User findByLogin(String login);
}
