package com.aladdinworks4.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.aladdinworks4.dao.GenericDAO;
import com.aladdinworks4.service.GenericService;
import com.aladdinworks4.service.impl.GenericServiceImpl;
import com.aladdinworks4.dao.CurrentAlertDAO;
import com.aladdinworks4.domain.CurrentAlert;
import com.aladdinworks4.dto.CurrentAlertDTO;
import com.aladdinworks4.dto.CurrentAlertSearchDTO;
import com.aladdinworks4.dto.CurrentAlertPageDTO;
import com.aladdinworks4.dto.CurrentAlertConvertCriteriaDTO;
import com.aladdinworks4.dto.common.RequestDTO;
import com.aladdinworks4.dto.common.ResultDTO;
import com.aladdinworks4.service.CurrentAlertService;
import com.aladdinworks4.util.ControllerUtils;





@Service
public class CurrentAlertServiceImpl extends GenericServiceImpl<CurrentAlert, Integer> implements CurrentAlertService {

    private final static Logger logger = LoggerFactory.getLogger(CurrentAlertServiceImpl.class);

	@Autowired
	CurrentAlertDAO currentAlertDao;

	


	@Override
	public GenericDAO<CurrentAlert, Integer> getDAO() {
		return (GenericDAO<CurrentAlert, Integer>) currentAlertDao;
	}
	
	public List<CurrentAlert> findAll () {
		List<CurrentAlert> currentAlerts = currentAlertDao.findAll();
		
		return currentAlerts;	
		
	}

	public ResultDTO addCurrentAlert(CurrentAlertDTO currentAlertDTO, RequestDTO requestDTO) {

		CurrentAlert currentAlert = new CurrentAlert();

		currentAlert.setCurrentAlertId(currentAlertDTO.getCurrentAlertId());


		currentAlert.setExceededCurrent(currentAlertDTO.getExceededCurrent());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		currentAlert = currentAlertDao.save(currentAlert);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<CurrentAlert> getAllCurrentAlerts(Pageable pageable) {
		return currentAlertDao.findAll(pageable);
	}

	public Page<CurrentAlert> getAllCurrentAlerts(Specification<CurrentAlert> spec, Pageable pageable) {
		return currentAlertDao.findAll(spec, pageable);
	}

	public ResponseEntity<CurrentAlertPageDTO> getCurrentAlerts(CurrentAlertSearchDTO currentAlertSearchDTO) {
	
			Integer currentAlertId = currentAlertSearchDTO.getCurrentAlertId(); 
  			String sortBy = currentAlertSearchDTO.getSortBy();
			String sortOrder = currentAlertSearchDTO.getSortOrder();
			String searchQuery = currentAlertSearchDTO.getSearchQuery();
			Integer page = currentAlertSearchDTO.getPage();
			Integer size = currentAlertSearchDTO.getSize();

	        Specification<CurrentAlert> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, currentAlertId, "currentAlertId"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<CurrentAlert> currentAlerts = this.getAllCurrentAlerts(spec, pageable);
		
		//System.out.println(String.valueOf(currentAlerts.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(currentAlerts.getTotalPages()));
		
		List<CurrentAlert> currentAlertsList = currentAlerts.getContent();
		
		CurrentAlertConvertCriteriaDTO convertCriteria = new CurrentAlertConvertCriteriaDTO();
		List<CurrentAlertDTO> currentAlertDTOs = this.convertCurrentAlertsToCurrentAlertDTOs(currentAlertsList,convertCriteria);
		
		CurrentAlertPageDTO currentAlertPageDTO = new CurrentAlertPageDTO();
		currentAlertPageDTO.setCurrentAlerts(currentAlertDTOs);
		currentAlertPageDTO.setTotalElements(currentAlerts.getTotalElements());
		return ResponseEntity.ok(currentAlertPageDTO);
	}

	public List<CurrentAlertDTO> convertCurrentAlertsToCurrentAlertDTOs(List<CurrentAlert> currentAlerts, CurrentAlertConvertCriteriaDTO convertCriteria) {
		
		List<CurrentAlertDTO> currentAlertDTOs = new ArrayList<CurrentAlertDTO>();
		
		for (CurrentAlert currentAlert : currentAlerts) {
			currentAlertDTOs.add(convertCurrentAlertToCurrentAlertDTO(currentAlert,convertCriteria));
		}
		
		return currentAlertDTOs;

	}
	
	public CurrentAlertDTO convertCurrentAlertToCurrentAlertDTO(CurrentAlert currentAlert, CurrentAlertConvertCriteriaDTO convertCriteria) {
		
		CurrentAlertDTO currentAlertDTO = new CurrentAlertDTO();
		
		currentAlertDTO.setCurrentAlertId(currentAlert.getCurrentAlertId());

	
		currentAlertDTO.setExceededCurrent(currentAlert.getExceededCurrent());

	

		
		return currentAlertDTO;
	}

	public ResultDTO updateCurrentAlert(CurrentAlertDTO currentAlertDTO, RequestDTO requestDTO) {
		
		CurrentAlert currentAlert = currentAlertDao.getById(currentAlertDTO.getCurrentAlertId());

		currentAlert.setCurrentAlertId(ControllerUtils.setValue(currentAlert.getCurrentAlertId(), currentAlertDTO.getCurrentAlertId()));

		currentAlert.setExceededCurrent(ControllerUtils.setValue(currentAlert.getExceededCurrent(), currentAlertDTO.getExceededCurrent()));



        currentAlert = currentAlertDao.save(currentAlert);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CurrentAlertDTO getCurrentAlertDTOById(Integer currentAlertId) {
	
		CurrentAlert currentAlert = currentAlertDao.getById(currentAlertId);
			
		
		CurrentAlertConvertCriteriaDTO convertCriteria = new CurrentAlertConvertCriteriaDTO();
		return(this.convertCurrentAlertToCurrentAlertDTO(currentAlert,convertCriteria));
	}







}
