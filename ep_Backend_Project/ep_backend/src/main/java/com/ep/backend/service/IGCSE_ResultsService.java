package com.ep.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ep.backend.Exception.ResourceNotFoundException;
import com.ep.backend.dto.IGCSE_ResultsDto;
import com.ep.backend.entities.IGCSE_Results;



@Service
public interface IGCSE_ResultsService {

	IGCSE_ResultsDto addIGCSE_Results(IGCSE_ResultsDto igcse_resultsDto);
	List<IGCSE_ResultsDto> getAllIGCSE_Results();
	List<IGCSE_Results> updateIGCSE_Results(IGCSE_ResultsDto igcse_resultsDto, String studentName) throws ResourceNotFoundException;
    List<IGCSE_ResultsDto> findResultsByStudentName(String studentName);
	boolean deleteAllIGCSE_Results();
	boolean deleteSingleIGCSE_Results(String studentName);
}

