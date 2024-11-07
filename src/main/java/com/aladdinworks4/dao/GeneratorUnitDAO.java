package com.aladdinworks4.dao;

import java.util.List;

import com.aladdinworks4.dao.GenericDAO;
import com.aladdinworks4.domain.GeneratorUnit;





public interface GeneratorUnitDAO extends GenericDAO<GeneratorUnit, Integer> {
  
	List<GeneratorUnit> findAll();
	






}


