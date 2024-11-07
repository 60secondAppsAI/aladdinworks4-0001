package com.aladdinworks4.dao;

import java.util.List;

import com.aladdinworks4.dao.GenericDAO;
import com.aladdinworks4.domain.MonitoringPoint;





public interface MonitoringPointDAO extends GenericDAO<MonitoringPoint, Integer> {
  
	List<MonitoringPoint> findAll();
	






}


