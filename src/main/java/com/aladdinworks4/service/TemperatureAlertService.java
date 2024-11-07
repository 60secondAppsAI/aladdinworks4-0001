package com.aladdinworks4.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks4.domain.TemperatureAlert;
import com.aladdinworks4.dto.TemperatureAlertDTO;
import com.aladdinworks4.dto.TemperatureAlertSearchDTO;
import com.aladdinworks4.dto.TemperatureAlertPageDTO;
import com.aladdinworks4.dto.TemperatureAlertConvertCriteriaDTO;
import com.aladdinworks4.service.GenericService;
import com.aladdinworks4.dto.common.RequestDTO;
import com.aladdinworks4.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface TemperatureAlertService extends GenericService<TemperatureAlert, Integer> {

	List<TemperatureAlert> findAll();

	ResultDTO addTemperatureAlert(TemperatureAlertDTO temperatureAlertDTO, RequestDTO requestDTO);

	ResultDTO updateTemperatureAlert(TemperatureAlertDTO temperatureAlertDTO, RequestDTO requestDTO);

    Page<TemperatureAlert> getAllTemperatureAlerts(Pageable pageable);

    Page<TemperatureAlert> getAllTemperatureAlerts(Specification<TemperatureAlert> spec, Pageable pageable);

	ResponseEntity<TemperatureAlertPageDTO> getTemperatureAlerts(TemperatureAlertSearchDTO temperatureAlertSearchDTO);
	
	List<TemperatureAlertDTO> convertTemperatureAlertsToTemperatureAlertDTOs(List<TemperatureAlert> temperatureAlerts, TemperatureAlertConvertCriteriaDTO convertCriteria);

	TemperatureAlertDTO getTemperatureAlertDTOById(Integer temperatureAlertId);







}





