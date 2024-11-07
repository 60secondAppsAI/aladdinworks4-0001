package com.aladdinworks4.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks4.domain.SwitchDevice;
import com.aladdinworks4.dto.SwitchDeviceDTO;
import com.aladdinworks4.dto.SwitchDeviceSearchDTO;
import com.aladdinworks4.dto.SwitchDevicePageDTO;
import com.aladdinworks4.dto.SwitchDeviceConvertCriteriaDTO;
import com.aladdinworks4.service.GenericService;
import com.aladdinworks4.dto.common.RequestDTO;
import com.aladdinworks4.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SwitchDeviceService extends GenericService<SwitchDevice, Integer> {

	List<SwitchDevice> findAll();

	ResultDTO addSwitchDevice(SwitchDeviceDTO switchDeviceDTO, RequestDTO requestDTO);

	ResultDTO updateSwitchDevice(SwitchDeviceDTO switchDeviceDTO, RequestDTO requestDTO);

    Page<SwitchDevice> getAllSwitchDevices(Pageable pageable);

    Page<SwitchDevice> getAllSwitchDevices(Specification<SwitchDevice> spec, Pageable pageable);

	ResponseEntity<SwitchDevicePageDTO> getSwitchDevices(SwitchDeviceSearchDTO switchDeviceSearchDTO);
	
	List<SwitchDeviceDTO> convertSwitchDevicesToSwitchDeviceDTOs(List<SwitchDevice> switchDevices, SwitchDeviceConvertCriteriaDTO convertCriteria);

	SwitchDeviceDTO getSwitchDeviceDTOById(Integer switchDeviceId);







}





