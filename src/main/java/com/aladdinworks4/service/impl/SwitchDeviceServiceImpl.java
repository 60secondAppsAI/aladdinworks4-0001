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
import com.aladdinworks4.dao.SwitchDeviceDAO;
import com.aladdinworks4.domain.SwitchDevice;
import com.aladdinworks4.dto.SwitchDeviceDTO;
import com.aladdinworks4.dto.SwitchDeviceSearchDTO;
import com.aladdinworks4.dto.SwitchDevicePageDTO;
import com.aladdinworks4.dto.SwitchDeviceConvertCriteriaDTO;
import com.aladdinworks4.dto.common.RequestDTO;
import com.aladdinworks4.dto.common.ResultDTO;
import com.aladdinworks4.service.SwitchDeviceService;
import com.aladdinworks4.util.ControllerUtils;





@Service
public class SwitchDeviceServiceImpl extends GenericServiceImpl<SwitchDevice, Integer> implements SwitchDeviceService {

    private final static Logger logger = LoggerFactory.getLogger(SwitchDeviceServiceImpl.class);

	@Autowired
	SwitchDeviceDAO switchDeviceDao;

	


	@Override
	public GenericDAO<SwitchDevice, Integer> getDAO() {
		return (GenericDAO<SwitchDevice, Integer>) switchDeviceDao;
	}
	
	public List<SwitchDevice> findAll () {
		List<SwitchDevice> switchDevices = switchDeviceDao.findAll();
		
		return switchDevices;	
		
	}

	public ResultDTO addSwitchDevice(SwitchDeviceDTO switchDeviceDTO, RequestDTO requestDTO) {

		SwitchDevice switchDevice = new SwitchDevice();

		switchDevice.setSwitchDeviceId(switchDeviceDTO.getSwitchDeviceId());


		switchDevice.setPorts(switchDeviceDTO.getPorts());


		switchDevice.setBandwidth(switchDeviceDTO.getBandwidth());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		switchDevice = switchDeviceDao.save(switchDevice);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<SwitchDevice> getAllSwitchDevices(Pageable pageable) {
		return switchDeviceDao.findAll(pageable);
	}

	public Page<SwitchDevice> getAllSwitchDevices(Specification<SwitchDevice> spec, Pageable pageable) {
		return switchDeviceDao.findAll(spec, pageable);
	}

	public ResponseEntity<SwitchDevicePageDTO> getSwitchDevices(SwitchDeviceSearchDTO switchDeviceSearchDTO) {
	
			Integer switchDeviceId = switchDeviceSearchDTO.getSwitchDeviceId(); 
   			String sortBy = switchDeviceSearchDTO.getSortBy();
			String sortOrder = switchDeviceSearchDTO.getSortOrder();
			String searchQuery = switchDeviceSearchDTO.getSearchQuery();
			Integer page = switchDeviceSearchDTO.getPage();
			Integer size = switchDeviceSearchDTO.getSize();

	        Specification<SwitchDevice> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, switchDeviceId, "switchDeviceId"); 
			
			
			

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

		Page<SwitchDevice> switchDevices = this.getAllSwitchDevices(spec, pageable);
		
		//System.out.println(String.valueOf(switchDevices.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(switchDevices.getTotalPages()));
		
		List<SwitchDevice> switchDevicesList = switchDevices.getContent();
		
		SwitchDeviceConvertCriteriaDTO convertCriteria = new SwitchDeviceConvertCriteriaDTO();
		List<SwitchDeviceDTO> switchDeviceDTOs = this.convertSwitchDevicesToSwitchDeviceDTOs(switchDevicesList,convertCriteria);
		
		SwitchDevicePageDTO switchDevicePageDTO = new SwitchDevicePageDTO();
		switchDevicePageDTO.setSwitchDevices(switchDeviceDTOs);
		switchDevicePageDTO.setTotalElements(switchDevices.getTotalElements());
		return ResponseEntity.ok(switchDevicePageDTO);
	}

	public List<SwitchDeviceDTO> convertSwitchDevicesToSwitchDeviceDTOs(List<SwitchDevice> switchDevices, SwitchDeviceConvertCriteriaDTO convertCriteria) {
		
		List<SwitchDeviceDTO> switchDeviceDTOs = new ArrayList<SwitchDeviceDTO>();
		
		for (SwitchDevice switchDevice : switchDevices) {
			switchDeviceDTOs.add(convertSwitchDeviceToSwitchDeviceDTO(switchDevice,convertCriteria));
		}
		
		return switchDeviceDTOs;

	}
	
	public SwitchDeviceDTO convertSwitchDeviceToSwitchDeviceDTO(SwitchDevice switchDevice, SwitchDeviceConvertCriteriaDTO convertCriteria) {
		
		SwitchDeviceDTO switchDeviceDTO = new SwitchDeviceDTO();
		
		switchDeviceDTO.setSwitchDeviceId(switchDevice.getSwitchDeviceId());

	
		switchDeviceDTO.setPorts(switchDevice.getPorts());

	
		switchDeviceDTO.setBandwidth(switchDevice.getBandwidth());

	

		
		return switchDeviceDTO;
	}

	public ResultDTO updateSwitchDevice(SwitchDeviceDTO switchDeviceDTO, RequestDTO requestDTO) {
		
		SwitchDevice switchDevice = switchDeviceDao.getById(switchDeviceDTO.getSwitchDeviceId());

		switchDevice.setSwitchDeviceId(ControllerUtils.setValue(switchDevice.getSwitchDeviceId(), switchDeviceDTO.getSwitchDeviceId()));

		switchDevice.setPorts(ControllerUtils.setValue(switchDevice.getPorts(), switchDeviceDTO.getPorts()));

		switchDevice.setBandwidth(ControllerUtils.setValue(switchDevice.getBandwidth(), switchDeviceDTO.getBandwidth()));



        switchDevice = switchDeviceDao.save(switchDevice);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public SwitchDeviceDTO getSwitchDeviceDTOById(Integer switchDeviceId) {
	
		SwitchDevice switchDevice = switchDeviceDao.getById(switchDeviceId);
			
		
		SwitchDeviceConvertCriteriaDTO convertCriteria = new SwitchDeviceConvertCriteriaDTO();
		return(this.convertSwitchDeviceToSwitchDeviceDTO(switchDevice,convertCriteria));
	}







}
