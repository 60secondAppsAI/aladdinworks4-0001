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
import com.aladdinworks4.dao.GeneratorUnitDAO;
import com.aladdinworks4.domain.GeneratorUnit;
import com.aladdinworks4.dto.GeneratorUnitDTO;
import com.aladdinworks4.dto.GeneratorUnitSearchDTO;
import com.aladdinworks4.dto.GeneratorUnitPageDTO;
import com.aladdinworks4.dto.GeneratorUnitConvertCriteriaDTO;
import com.aladdinworks4.dto.common.RequestDTO;
import com.aladdinworks4.dto.common.ResultDTO;
import com.aladdinworks4.service.GeneratorUnitService;
import com.aladdinworks4.util.ControllerUtils;





@Service
public class GeneratorUnitServiceImpl extends GenericServiceImpl<GeneratorUnit, Integer> implements GeneratorUnitService {

    private final static Logger logger = LoggerFactory.getLogger(GeneratorUnitServiceImpl.class);

	@Autowired
	GeneratorUnitDAO generatorUnitDao;

	


	@Override
	public GenericDAO<GeneratorUnit, Integer> getDAO() {
		return (GenericDAO<GeneratorUnit, Integer>) generatorUnitDao;
	}
	
	public List<GeneratorUnit> findAll () {
		List<GeneratorUnit> generatorUnits = generatorUnitDao.findAll();
		
		return generatorUnits;	
		
	}

	public ResultDTO addGeneratorUnit(GeneratorUnitDTO generatorUnitDTO, RequestDTO requestDTO) {

		GeneratorUnit generatorUnit = new GeneratorUnit();

		generatorUnit.setGeneratorUnitId(generatorUnitDTO.getGeneratorUnitId());


		generatorUnit.setPowerOutput(generatorUnitDTO.getPowerOutput());


		generatorUnit.setFuelType(generatorUnitDTO.getFuelType());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		generatorUnit = generatorUnitDao.save(generatorUnit);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<GeneratorUnit> getAllGeneratorUnits(Pageable pageable) {
		return generatorUnitDao.findAll(pageable);
	}

	public Page<GeneratorUnit> getAllGeneratorUnits(Specification<GeneratorUnit> spec, Pageable pageable) {
		return generatorUnitDao.findAll(spec, pageable);
	}

	public ResponseEntity<GeneratorUnitPageDTO> getGeneratorUnits(GeneratorUnitSearchDTO generatorUnitSearchDTO) {
	
			Integer generatorUnitId = generatorUnitSearchDTO.getGeneratorUnitId(); 
  			String fuelType = generatorUnitSearchDTO.getFuelType(); 
 			String sortBy = generatorUnitSearchDTO.getSortBy();
			String sortOrder = generatorUnitSearchDTO.getSortOrder();
			String searchQuery = generatorUnitSearchDTO.getSearchQuery();
			Integer page = generatorUnitSearchDTO.getPage();
			Integer size = generatorUnitSearchDTO.getSize();

	        Specification<GeneratorUnit> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, generatorUnitId, "generatorUnitId"); 
			
			
			spec = ControllerUtils.andIfNecessary(spec, fuelType, "fuelType"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("fuelType")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<GeneratorUnit> generatorUnits = this.getAllGeneratorUnits(spec, pageable);
		
		//System.out.println(String.valueOf(generatorUnits.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(generatorUnits.getTotalPages()));
		
		List<GeneratorUnit> generatorUnitsList = generatorUnits.getContent();
		
		GeneratorUnitConvertCriteriaDTO convertCriteria = new GeneratorUnitConvertCriteriaDTO();
		List<GeneratorUnitDTO> generatorUnitDTOs = this.convertGeneratorUnitsToGeneratorUnitDTOs(generatorUnitsList,convertCriteria);
		
		GeneratorUnitPageDTO generatorUnitPageDTO = new GeneratorUnitPageDTO();
		generatorUnitPageDTO.setGeneratorUnits(generatorUnitDTOs);
		generatorUnitPageDTO.setTotalElements(generatorUnits.getTotalElements());
		return ResponseEntity.ok(generatorUnitPageDTO);
	}

	public List<GeneratorUnitDTO> convertGeneratorUnitsToGeneratorUnitDTOs(List<GeneratorUnit> generatorUnits, GeneratorUnitConvertCriteriaDTO convertCriteria) {
		
		List<GeneratorUnitDTO> generatorUnitDTOs = new ArrayList<GeneratorUnitDTO>();
		
		for (GeneratorUnit generatorUnit : generatorUnits) {
			generatorUnitDTOs.add(convertGeneratorUnitToGeneratorUnitDTO(generatorUnit,convertCriteria));
		}
		
		return generatorUnitDTOs;

	}
	
	public GeneratorUnitDTO convertGeneratorUnitToGeneratorUnitDTO(GeneratorUnit generatorUnit, GeneratorUnitConvertCriteriaDTO convertCriteria) {
		
		GeneratorUnitDTO generatorUnitDTO = new GeneratorUnitDTO();
		
		generatorUnitDTO.setGeneratorUnitId(generatorUnit.getGeneratorUnitId());

	
		generatorUnitDTO.setPowerOutput(generatorUnit.getPowerOutput());

	
		generatorUnitDTO.setFuelType(generatorUnit.getFuelType());

	

		
		return generatorUnitDTO;
	}

	public ResultDTO updateGeneratorUnit(GeneratorUnitDTO generatorUnitDTO, RequestDTO requestDTO) {
		
		GeneratorUnit generatorUnit = generatorUnitDao.getById(generatorUnitDTO.getGeneratorUnitId());

		generatorUnit.setGeneratorUnitId(ControllerUtils.setValue(generatorUnit.getGeneratorUnitId(), generatorUnitDTO.getGeneratorUnitId()));

		generatorUnit.setPowerOutput(ControllerUtils.setValue(generatorUnit.getPowerOutput(), generatorUnitDTO.getPowerOutput()));

		generatorUnit.setFuelType(ControllerUtils.setValue(generatorUnit.getFuelType(), generatorUnitDTO.getFuelType()));



        generatorUnit = generatorUnitDao.save(generatorUnit);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public GeneratorUnitDTO getGeneratorUnitDTOById(Integer generatorUnitId) {
	
		GeneratorUnit generatorUnit = generatorUnitDao.getById(generatorUnitId);
			
		
		GeneratorUnitConvertCriteriaDTO convertCriteria = new GeneratorUnitConvertCriteriaDTO();
		return(this.convertGeneratorUnitToGeneratorUnitDTO(generatorUnit,convertCriteria));
	}







}
