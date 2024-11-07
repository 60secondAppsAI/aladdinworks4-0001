package com.aladdinworks4.dao;

import java.util.List;

import com.aladdinworks4.dao.GenericDAO;
import com.aladdinworks4.domain.CurrentAlert;





public interface CurrentAlertDAO extends GenericDAO<CurrentAlert, Integer> {
  
	List<CurrentAlert> findAll();
	






}


