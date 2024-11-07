package com.aladdinworks4.dao;

import java.util.List;

import com.aladdinworks4.dao.GenericDAO;
import com.aladdinworks4.domain.PowerSupply;





public interface PowerSupplyDAO extends GenericDAO<PowerSupply, Integer> {
  
	List<PowerSupply> findAll();
	






}


