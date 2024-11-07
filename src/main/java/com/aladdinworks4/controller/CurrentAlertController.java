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

import com.aladdinworks4.domain.CurrentAlert;
import com.aladdinworks4.dto.CurrentAlertDTO;
import com.aladdinworks4.dto.CurrentAlertSearchDTO;
import com.aladdinworks4.dto.CurrentAlertPageDTO;
import com.aladdinworks4.service.CurrentAlertService;
import com.aladdinworks4.dto.common.RequestDTO;
import com.aladdinworks4.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/currentAlert")
@RestController
public class CurrentAlertController {

	private final static Logger logger = LoggerFactory.getLogger(CurrentAlertController.class);

	@Autowired
	CurrentAlertService currentAlertService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<CurrentAlert> getAll() {

		List<CurrentAlert> currentAlerts = currentAlertService.findAll();
		
		return currentAlerts;	
	}

	@GetMapping(value = "/{currentAlertId}")
	@ResponseBody
	public CurrentAlertDTO getCurrentAlert(@PathVariable Integer currentAlertId) {
		
		return (currentAlertService.getCurrentAlertDTOById(currentAlertId));
	}

 	@RequestMapping(value = "/addCurrentAlert", method = RequestMethod.POST)
	public ResponseEntity<?> addCurrentAlert(@RequestBody CurrentAlertDTO currentAlertDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = currentAlertService.addCurrentAlert(currentAlertDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/currentAlerts")
	public ResponseEntity<CurrentAlertPageDTO> getCurrentAlerts(CurrentAlertSearchDTO currentAlertSearchDTO) {
 
		return currentAlertService.getCurrentAlerts(currentAlertSearchDTO);
	}	

	@RequestMapping(value = "/updateCurrentAlert", method = RequestMethod.POST)
	public ResponseEntity<?> updateCurrentAlert(@RequestBody CurrentAlertDTO currentAlertDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = currentAlertService.updateCurrentAlert(currentAlertDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
