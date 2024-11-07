package com.aladdinworks4.dao;

import java.util.List;

import com.aladdinworks4.dao.GenericDAO;
import com.aladdinworks4.domain.CurrentSensor;





public interface CurrentSensorDAO extends GenericDAO<CurrentSensor, Integer> {
  
	List<CurrentSensor> findAll();
	






}


