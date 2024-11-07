package com.aladdinworks4.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks4.domain.CurrentAlert;
import com.aladdinworks4.dto.CurrentAlertDTO;
import com.aladdinworks4.dto.CurrentAlertSearchDTO;
import com.aladdinworks4.dto.CurrentAlertPageDTO;
import com.aladdinworks4.dto.CurrentAlertConvertCriteriaDTO;
import com.aladdinworks4.service.GenericService;
import com.aladdinworks4.dto.common.RequestDTO;
import com.aladdinworks4.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CurrentAlertService extends GenericService<CurrentAlert, Integer> {

	List<CurrentAlert> findAll();

	ResultDTO addCurrentAlert(CurrentAlertDTO currentAlertDTO, RequestDTO requestDTO);

	ResultDTO updateCurrentAlert(CurrentAlertDTO currentAlertDTO, RequestDTO requestDTO);

    Page<CurrentAlert> getAllCurrentAlerts(Pageable pageable);

    Page<CurrentAlert> getAllCurrentAlerts(Specification<CurrentAlert> spec, Pageable pageable);

	ResponseEntity<CurrentAlertPageDTO> getCurrentAlerts(CurrentAlertSearchDTO currentAlertSearchDTO);
	
	List<CurrentAlertDTO> convertCurrentAlertsToCurrentAlertDTOs(List<CurrentAlert> currentAlerts, CurrentAlertConvertCriteriaDTO convertCriteria);

	CurrentAlertDTO getCurrentAlertDTOById(Integer currentAlertId);







}





