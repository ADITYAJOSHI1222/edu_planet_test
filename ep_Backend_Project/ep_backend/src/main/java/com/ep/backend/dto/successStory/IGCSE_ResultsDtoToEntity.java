package com.ep.backend.dto.successStory;


import org.springframework.stereotype.Component;

import com.ep.backend.entities.successStory.IGCSE_Results;
@Component
public class IGCSE_ResultsDtoToEntity {
	
	public IGCSE_Results dtoToIGCSEResult(IGCSE_ResultsDto igcseResultDto) {
		IGCSE_Results igcseResult = new IGCSE_Results();
		
		igcseResult.setStudentName(igcseResultDto.getStudentName());
		igcseResult.setStudent_School(igcseResultDto.getStudent_School());
		igcseResult.setYear(igcseResultDto.getYear());
		igcseResult.setExtented(igcseResultDto.getExtented());
		igcseResult.setAdditional(igcseResult.getAdditional());
		igcseResult.setInternational(igcseResultDto.getInternational());
		igcseResult.setStatus(igcseResultDto.isStatus());
		
		return igcseResult;
	}

	public IGCSE_ResultsDto igcseResultToDto(IGCSE_Results igcseResult) {
		
		IGCSE_ResultsDto igcseResultDto = new IGCSE_ResultsDto();
		
		igcseResultDto.setStudentName(igcseResult.getStudentName());
		igcseResultDto.setStudent_School(igcseResult.getStudent_School());
		igcseResultDto.setYear(igcseResult.getYear());
		igcseResultDto.setExtented(igcseResult.getExtented());
		igcseResultDto.setAdditional(igcseResult.getAdditional());
		igcseResultDto.setInternational(igcseResult.getInternational());
		igcseResultDto.setStatus(igcseResult.isStatus());
		
		return igcseResultDto;
	}
}
