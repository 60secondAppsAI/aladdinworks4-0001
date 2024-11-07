package com.aladdinworks4.dao;

import java.util.List;

import com.aladdinworks4.dao.GenericDAO;
import com.aladdinworks4.domain.CoolingUnit;





public interface CoolingUnitDAO extends GenericDAO<CoolingUnit, Integer> {
  
	List<CoolingUnit> findAll();
	






}


