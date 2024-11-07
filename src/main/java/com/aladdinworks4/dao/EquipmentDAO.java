package com.aladdinworks4.dao;

import java.util.List;

import com.aladdinworks4.dao.GenericDAO;
import com.aladdinworks4.domain.Equipment;





public interface EquipmentDAO extends GenericDAO<Equipment, Integer> {
  
	List<Equipment> findAll();
	






}


