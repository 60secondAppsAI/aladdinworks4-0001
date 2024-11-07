package com.aladdinworks4.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks4.domain.Equipment;
import com.aladdinworks4.dto.EquipmentDTO;
import com.aladdinworks4.dto.EquipmentSearchDTO;
import com.aladdinworks4.dto.EquipmentPageDTO;
import com.aladdinworks4.dto.EquipmentConvertCriteriaDTO;
import com.aladdinworks4.service.GenericService;
import com.aladdinworks4.dto.common.RequestDTO;
import com.aladdinworks4.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface EquipmentService extends GenericService<Equipment, Integer> {

	List<Equipment> findAll();

	ResultDTO addEquipment(EquipmentDTO equipmentDTO, RequestDTO requestDTO);

	ResultDTO updateEquipment(EquipmentDTO equipmentDTO, RequestDTO requestDTO);

    Page<Equipment> getAllEquipments(Pageable pageable);

    Page<Equipment> getAllEquipments(Specification<Equipment> spec, Pageable pageable);

	ResponseEntity<EquipmentPageDTO> getEquipments(EquipmentSearchDTO equipmentSearchDTO);
	
	List<EquipmentDTO> convertEquipmentsToEquipmentDTOs(List<Equipment> equipments, EquipmentConvertCriteriaDTO convertCriteria);

	EquipmentDTO getEquipmentDTOById(Integer equipmentId);







}





