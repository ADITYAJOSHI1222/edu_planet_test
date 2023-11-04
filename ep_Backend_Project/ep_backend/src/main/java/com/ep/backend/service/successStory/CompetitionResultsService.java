package com.ep.backend.service.successStory;

import java.util.List;

import com.ep.backend.Exception.ResourceNotFoundException;
import com.ep.backend.dto.successStory.CompetitionResultsDto;



public interface CompetitionResultsService {

	CompetitionResultsDto addCompetitionResult(CompetitionResultsDto competitionResultsDto);
	List<CompetitionResultsDto> getAllCompetitionResults();
	List<CompetitionResultsDto>findByStudentName(String studentName);
	CompetitionResultsDto updateCompetitionResults(CompetitionResultsDto competitionResultsDto, String studentName) throws ResourceNotFoundException;
	boolean deleteAllCompetitionResults();
	boolean deleteSingleCompetitionResults(String studentName);
}
