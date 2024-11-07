package com.aladdinworks4.dao;

import java.util.List;

import com.aladdinworks4.dao.GenericDAO;
import com.aladdinworks4.domain.TemperatureAlert;





public interface TemperatureAlertDAO extends GenericDAO<TemperatureAlert, Integer> {
  
	List<TemperatureAlert> findAll();
	






}


