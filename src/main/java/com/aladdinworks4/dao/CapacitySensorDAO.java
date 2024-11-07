package com.aladdinworks4.dao;

import java.util.List;

import com.aladdinworks4.dao.GenericDAO;
import com.aladdinworks4.domain.CapacitySensor;





public interface CapacitySensorDAO extends GenericDAO<CapacitySensor, Integer> {
  
	List<CapacitySensor> findAll();
	






}


