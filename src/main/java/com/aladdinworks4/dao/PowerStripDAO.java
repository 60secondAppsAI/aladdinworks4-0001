package com.aladdinworks4.dao;

import java.util.List;

import com.aladdinworks4.dao.GenericDAO;
import com.aladdinworks4.domain.PowerStrip;





public interface PowerStripDAO extends GenericDAO<PowerStrip, Integer> {
  
	List<PowerStrip> findAll();
	






}


