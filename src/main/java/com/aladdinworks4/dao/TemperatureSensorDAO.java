package com.aladdinworks4.dao;

import java.util.List;

import com.aladdinworks4.dao.GenericDAO;
import com.aladdinworks4.domain.TemperatureSensor;





public interface TemperatureSensorDAO extends GenericDAO<TemperatureSensor, Integer> {
  
	List<TemperatureSensor> findAll();
	






}


