package com.aladdinworks4.dao;

import java.util.List;

import com.aladdinworks4.dao.GenericDAO;
import com.aladdinworks4.domain.MaintenanceRecord;





public interface MaintenanceRecordDAO extends GenericDAO<MaintenanceRecord, Integer> {
  
	List<MaintenanceRecord> findAll();
	






}


