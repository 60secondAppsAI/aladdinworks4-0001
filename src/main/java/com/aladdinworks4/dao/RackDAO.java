package com.aladdinworks4.dao;

import java.util.List;

import com.aladdinworks4.dao.GenericDAO;
import com.aladdinworks4.domain.Rack;





public interface RackDAO extends GenericDAO<Rack, Integer> {
  
	List<Rack> findAll();
	






}


