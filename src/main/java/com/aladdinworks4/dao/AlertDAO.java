package com.aladdinworks4.dao;

import java.util.List;

import com.aladdinworks4.dao.GenericDAO;
import com.aladdinworks4.domain.Alert;





public interface AlertDAO extends GenericDAO<Alert, Integer> {
  
	List<Alert> findAll();
	






}


