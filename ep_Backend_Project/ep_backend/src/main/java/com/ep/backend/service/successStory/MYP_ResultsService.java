package com.ep.backend.service.successStory;

import java.util.List;

import com.ep.backend.Exception.ResourceNotFoundException;
import com.ep.backend.dto.successStory.MYP_ResultsDto;



public interface MYP_ResultsService {

	MYP_ResultsDto addMYP_Result(MYP_ResultsDto myp_resultsDto);
	List<MYP_ResultsDto> getAllMYP_Results();
	List<MYP_ResultsDto> findByStudentName(String studentName);
	MYP_ResultsDto updateMYP_Results(MYP_ResultsDto myp_resultsDto, String studentName) throws ResourceNotFoundException;
    boolean deleteAllMYP_Results();
	boolean deleteSingleMYP_Result(String studentName);	
}
