package com.aladdinworks4.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks4.domain.CapacitySensor;
import com.aladdinworks4.dto.CapacitySensorDTO;
import com.aladdinworks4.dto.CapacitySensorSearchDTO;
import com.aladdinworks4.dto.CapacitySensorPageDTO;
import com.aladdinworks4.dto.CapacitySensorConvertCriteriaDTO;
import com.aladdinworks4.service.GenericService;
import com.aladdinworks4.dto.common.RequestDTO;
import com.aladdinworks4.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CapacitySensorService extends GenericService<CapacitySensor, Integer> {

	List<CapacitySensor> findAll();

	ResultDTO addCapacitySensor(CapacitySensorDTO capacitySensorDTO, RequestDTO requestDTO);

	ResultDTO updateCapacitySensor(CapacitySensorDTO capacitySensorDTO, RequestDTO requestDTO);

    Page<CapacitySensor> getAllCapacitySensors(Pageable pageable);

    Page<CapacitySensor> getAllCapacitySensors(Specification<CapacitySensor> spec, Pageable pageable);

	ResponseEntity<CapacitySensorPageDTO> getCapacitySensors(CapacitySensorSearchDTO capacitySensorSearchDTO);
	
	List<CapacitySensorDTO> convertCapacitySensorsToCapacitySensorDTOs(List<CapacitySensor> capacitySensors, CapacitySensorConvertCriteriaDTO convertCriteria);

	CapacitySensorDTO getCapacitySensorDTOById(Integer capacitySensorId);







}





