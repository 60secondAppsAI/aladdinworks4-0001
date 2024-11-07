package com.aladdinworks4.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks4.domain.GeneratorUnit;
import com.aladdinworks4.dto.GeneratorUnitDTO;
import com.aladdinworks4.dto.GeneratorUnitSearchDTO;
import com.aladdinworks4.dto.GeneratorUnitPageDTO;
import com.aladdinworks4.dto.GeneratorUnitConvertCriteriaDTO;
import com.aladdinworks4.service.GenericService;
import com.aladdinworks4.dto.common.RequestDTO;
import com.aladdinworks4.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface GeneratorUnitService extends GenericService<GeneratorUnit, Integer> {

	List<GeneratorUnit> findAll();

	ResultDTO addGeneratorUnit(GeneratorUnitDTO generatorUnitDTO, RequestDTO requestDTO);

	ResultDTO updateGeneratorUnit(GeneratorUnitDTO generatorUnitDTO, RequestDTO requestDTO);

    Page<GeneratorUnit> getAllGeneratorUnits(Pageable pageable);

    Page<GeneratorUnit> getAllGeneratorUnits(Specification<GeneratorUnit> spec, Pageable pageable);

	ResponseEntity<GeneratorUnitPageDTO> getGeneratorUnits(GeneratorUnitSearchDTO generatorUnitSearchDTO);
	
	List<GeneratorUnitDTO> convertGeneratorUnitsToGeneratorUnitDTOs(List<GeneratorUnit> generatorUnits, GeneratorUnitConvertCriteriaDTO convertCriteria);

	GeneratorUnitDTO getGeneratorUnitDTOById(Integer generatorUnitId);







}





