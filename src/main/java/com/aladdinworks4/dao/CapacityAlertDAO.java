package com.aladdinworks4.dao;

import java.util.List;

import com.aladdinworks4.dao.GenericDAO;
import com.aladdinworks4.domain.CapacityAlert;





public interface CapacityAlertDAO extends GenericDAO<CapacityAlert, Integer> {
  
	List<CapacityAlert> findAll();
	






}


