package com.aladdinworks4.dao;

import java.util.List;

import com.aladdinworks4.dao.GenericDAO;
import com.aladdinworks4.domain.SwitchDevice;





public interface SwitchDeviceDAO extends GenericDAO<SwitchDevice, Integer> {
  
	List<SwitchDevice> findAll();
	






}


