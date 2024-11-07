package com.aladdinworks4.dao;

import java.util.List;

import com.aladdinworks4.dao.GenericDAO;
import com.aladdinworks4.domain.User;

import java.util.Optional;




public interface UserDAO extends GenericDAO<User, Integer> {
  
	List<User> findAll();
	






}


