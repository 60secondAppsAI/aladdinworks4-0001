package com.aladdinworks4.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks4.domain.IncidentReport;
import com.aladdinworks4.dto.IncidentReportDTO;
import com.aladdinworks4.dto.IncidentReportSearchDTO;
import com.aladdinworks4.dto.IncidentReportPageDTO;
import com.aladdinworks4.dto.IncidentReportConvertCriteriaDTO;
import com.aladdinworks4.service.GenericService;
import com.aladdinworks4.dto.common.RequestDTO;
import com.aladdinworks4.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface IncidentReportService extends GenericService<IncidentReport, Integer> {

	List<IncidentReport> findAll();

	ResultDTO addIncidentReport(IncidentReportDTO incidentReportDTO, RequestDTO requestDTO);

	ResultDTO updateIncidentReport(IncidentReportDTO incidentReportDTO, RequestDTO requestDTO);

    Page<IncidentReport> getAllIncidentReports(Pageable pageable);

    Page<IncidentReport> getAllIncidentReports(Specification<IncidentReport> spec, Pageable pageable);

	ResponseEntity<IncidentReportPageDTO> getIncidentReports(IncidentReportSearchDTO incidentReportSearchDTO);
	
	List<IncidentReportDTO> convertIncidentReportsToIncidentReportDTOs(List<IncidentReport> incidentReports, IncidentReportConvertCriteriaDTO convertCriteria);

	IncidentReportDTO getIncidentReportDTOById(Integer incidentReportId);







}





