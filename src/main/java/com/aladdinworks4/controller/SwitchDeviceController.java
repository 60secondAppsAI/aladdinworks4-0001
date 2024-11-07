package com.aladdinworks4.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.aladdinworks4.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.aladdinworks4.domain.SwitchDevice;
import com.aladdinworks4.dto.SwitchDeviceDTO;
import com.aladdinworks4.dto.SwitchDeviceSearchDTO;
import com.aladdinworks4.dto.SwitchDevicePageDTO;
import com.aladdinworks4.service.SwitchDeviceService;
import com.aladdinworks4.dto.common.RequestDTO;
import com.aladdinworks4.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/switchDevice")
@RestController
public class SwitchDeviceController {

	private final static Logger logger = LoggerFactory.getLogger(SwitchDeviceController.class);

	@Autowired
	SwitchDeviceService switchDeviceService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<SwitchDevice> getAll() {

		List<SwitchDevice> switchDevices = switchDeviceService.findAll();
		
		return switchDevices;	
	}

	@GetMapping(value = "/{switchDeviceId}")
	@ResponseBody
	public SwitchDeviceDTO getSwitchDevice(@PathVariable Integer switchDeviceId) {
		
		return (switchDeviceService.getSwitchDeviceDTOById(switchDeviceId));
	}

 	@RequestMapping(value = "/addSwitchDevice", method = RequestMethod.POST)
	public ResponseEntity<?> addSwitchDevice(@RequestBody SwitchDeviceDTO switchDeviceDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = switchDeviceService.addSwitchDevice(switchDeviceDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/switchDevices")
	public ResponseEntity<SwitchDevicePageDTO> getSwitchDevices(SwitchDeviceSearchDTO switchDeviceSearchDTO) {
 
		return switchDeviceService.getSwitchDevices(switchDeviceSearchDTO);
	}	

	@RequestMapping(value = "/updateSwitchDevice", method = RequestMethod.POST)
	public ResponseEntity<?> updateSwitchDevice(@RequestBody SwitchDeviceDTO switchDeviceDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = switchDeviceService.updateSwitchDevice(switchDeviceDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
