package com.aladdinworks4.dao;

import java.util.List;

import com.aladdinworks4.dao.GenericDAO;
import com.aladdinworks4.domain.IncidentReport;





public interface IncidentReportDAO extends GenericDAO<IncidentReport, Integer> {
  
	List<IncidentReport> findAll();
	






}


