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

import com.aladdinworks4.domain.GeneratorUnit;
import com.aladdinworks4.dto.GeneratorUnitDTO;
import com.aladdinworks4.dto.GeneratorUnitSearchDTO;
import com.aladdinworks4.dto.GeneratorUnitPageDTO;
import com.aladdinworks4.service.GeneratorUnitService;
import com.aladdinworks4.dto.common.RequestDTO;
import com.aladdinworks4.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/generatorUnit")
@RestController
public class GeneratorUnitController {

	private final static Logger logger = LoggerFactory.getLogger(GeneratorUnitController.class);

	@Autowired
	GeneratorUnitService generatorUnitService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<GeneratorUnit> getAll() {

		List<GeneratorUnit> generatorUnits = generatorUnitService.findAll();
		
		return generatorUnits;	
	}

	@GetMapping(value = "/{generatorUnitId}")
	@ResponseBody
	public GeneratorUnitDTO getGeneratorUnit(@PathVariable Integer generatorUnitId) {
		
		return (generatorUnitService.getGeneratorUnitDTOById(generatorUnitId));
	}

 	@RequestMapping(value = "/addGeneratorUnit", method = RequestMethod.POST)
	public ResponseEntity<?> addGeneratorUnit(@RequestBody GeneratorUnitDTO generatorUnitDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = generatorUnitService.addGeneratorUnit(generatorUnitDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/generatorUnits")
	public ResponseEntity<GeneratorUnitPageDTO> getGeneratorUnits(GeneratorUnitSearchDTO generatorUnitSearchDTO) {
 
		return generatorUnitService.getGeneratorUnits(generatorUnitSearchDTO);
	}	

	@RequestMapping(value = "/updateGeneratorUnit", method = RequestMethod.POST)
	public ResponseEntity<?> updateGeneratorUnit(@RequestBody GeneratorUnitDTO generatorUnitDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = generatorUnitService.updateGeneratorUnit(generatorUnitDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
