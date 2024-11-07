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
import com.aladdinworks4.dao.TemperatureSensorDAO;
import com.aladdinworks4.domain.TemperatureSensor;
import com.aladdinworks4.dto.TemperatureSensorDTO;
import com.aladdinworks4.dto.TemperatureSensorSearchDTO;
import com.aladdinworks4.dto.TemperatureSensorPageDTO;
import com.aladdinworks4.dto.TemperatureSensorConvertCriteriaDTO;
import com.aladdinworks4.dto.common.RequestDTO;
import com.aladdinworks4.dto.common.ResultDTO;
import com.aladdinworks4.service.TemperatureSensorService;
import com.aladdinworks4.util.ControllerUtils;





@Service
public class TemperatureSensorServiceImpl extends GenericServiceImpl<TemperatureSensor, Integer> implements TemperatureSensorService {

    private final static Logger logger = LoggerFactory.getLogger(TemperatureSensorServiceImpl.class);

	@Autowired
	TemperatureSensorDAO temperatureSensorDao;

	


	@Override
	public GenericDAO<TemperatureSensor, Integer> getDAO() {
		return (GenericDAO<TemperatureSensor, Integer>) temperatureSensorDao;
	}
	
	public List<TemperatureSensor> findAll () {
		List<TemperatureSensor> temperatureSensors = temperatureSensorDao.findAll();
		
		return temperatureSensors;	
		
	}

	public ResultDTO addTemperatureSensor(TemperatureSensorDTO temperatureSensorDTO, RequestDTO requestDTO) {

		TemperatureSensor temperatureSensor = new TemperatureSensor();

		temperatureSensor.setTemperatureSensorId(temperatureSensorDTO.getTemperatureSensorId());


		temperatureSensor.setSensorType(temperatureSensorDTO.getSensorType());


		temperatureSensor.setMinTemperature(temperatureSensorDTO.getMinTemperature());


		temperatureSensor.setMaxTemperature(temperatureSensorDTO.getMaxTemperature());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		temperatureSensor = temperatureSensorDao.save(temperatureSensor);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<TemperatureSensor> getAllTemperatureSensors(Pageable pageable) {
		return temperatureSensorDao.findAll(pageable);
	}

	public Page<TemperatureSensor> getAllTemperatureSensors(Specification<TemperatureSensor> spec, Pageable pageable) {
		return temperatureSensorDao.findAll(spec, pageable);
	}

	public ResponseEntity<TemperatureSensorPageDTO> getTemperatureSensors(TemperatureSensorSearchDTO temperatureSensorSearchDTO) {
	
			Integer temperatureSensorId = temperatureSensorSearchDTO.getTemperatureSensorId(); 
 			String sensorType = temperatureSensorSearchDTO.getSensorType(); 
   			String sortBy = temperatureSensorSearchDTO.getSortBy();
			String sortOrder = temperatureSensorSearchDTO.getSortOrder();
			String searchQuery = temperatureSensorSearchDTO.getSearchQuery();
			Integer page = temperatureSensorSearchDTO.getPage();
			Integer size = temperatureSensorSearchDTO.getSize();

	        Specification<TemperatureSensor> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, temperatureSensorId, "temperatureSensorId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, sensorType, "sensorType"); 
			
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("sensorType")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<TemperatureSensor> temperatureSensors = this.getAllTemperatureSensors(spec, pageable);
		
		//System.out.println(String.valueOf(temperatureSensors.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(temperatureSensors.getTotalPages()));
		
		List<TemperatureSensor> temperatureSensorsList = temperatureSensors.getContent();
		
		TemperatureSensorConvertCriteriaDTO convertCriteria = new TemperatureSensorConvertCriteriaDTO();
		List<TemperatureSensorDTO> temperatureSensorDTOs = this.convertTemperatureSensorsToTemperatureSensorDTOs(temperatureSensorsList,convertCriteria);
		
		TemperatureSensorPageDTO temperatureSensorPageDTO = new TemperatureSensorPageDTO();
		temperatureSensorPageDTO.setTemperatureSensors(temperatureSensorDTOs);
		temperatureSensorPageDTO.setTotalElements(temperatureSensors.getTotalElements());
		return ResponseEntity.ok(temperatureSensorPageDTO);
	}

	public List<TemperatureSensorDTO> convertTemperatureSensorsToTemperatureSensorDTOs(List<TemperatureSensor> temperatureSensors, TemperatureSensorConvertCriteriaDTO convertCriteria) {
		
		List<TemperatureSensorDTO> temperatureSensorDTOs = new ArrayList<TemperatureSensorDTO>();
		
		for (TemperatureSensor temperatureSensor : temperatureSensors) {
			temperatureSensorDTOs.add(convertTemperatureSensorToTemperatureSensorDTO(temperatureSensor,convertCriteria));
		}
		
		return temperatureSensorDTOs;

	}
	
	public TemperatureSensorDTO convertTemperatureSensorToTemperatureSensorDTO(TemperatureSensor temperatureSensor, TemperatureSensorConvertCriteriaDTO convertCriteria) {
		
		TemperatureSensorDTO temperatureSensorDTO = new TemperatureSensorDTO();
		
		temperatureSensorDTO.setTemperatureSensorId(temperatureSensor.getTemperatureSensorId());

	
		temperatureSensorDTO.setSensorType(temperatureSensor.getSensorType());

	
		temperatureSensorDTO.setMinTemperature(temperatureSensor.getMinTemperature());

	
		temperatureSensorDTO.setMaxTemperature(temperatureSensor.getMaxTemperature());

	

		
		return temperatureSensorDTO;
	}

	public ResultDTO updateTemperatureSensor(TemperatureSensorDTO temperatureSensorDTO, RequestDTO requestDTO) {
		
		TemperatureSensor temperatureSensor = temperatureSensorDao.getById(temperatureSensorDTO.getTemperatureSensorId());

		temperatureSensor.setTemperatureSensorId(ControllerUtils.setValue(temperatureSensor.getTemperatureSensorId(), temperatureSensorDTO.getTemperatureSensorId()));

		temperatureSensor.setSensorType(ControllerUtils.setValue(temperatureSensor.getSensorType(), temperatureSensorDTO.getSensorType()));

		temperatureSensor.setMinTemperature(ControllerUtils.setValue(temperatureSensor.getMinTemperature(), temperatureSensorDTO.getMinTemperature()));

		temperatureSensor.setMaxTemperature(ControllerUtils.setValue(temperatureSensor.getMaxTemperature(), temperatureSensorDTO.getMaxTemperature()));



        temperatureSensor = temperatureSensorDao.save(temperatureSensor);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public TemperatureSensorDTO getTemperatureSensorDTOById(Integer temperatureSensorId) {
	
		TemperatureSensor temperatureSensor = temperatureSensorDao.getById(temperatureSensorId);
			
		
		TemperatureSensorConvertCriteriaDTO convertCriteria = new TemperatureSensorConvertCriteriaDTO();
		return(this.convertTemperatureSensorToTemperatureSensorDTO(temperatureSensor,convertCriteria));
	}







}
