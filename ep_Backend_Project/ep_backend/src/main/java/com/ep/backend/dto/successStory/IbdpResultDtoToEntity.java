package com.ep.backend.dto.successStory;

import org.springframework.stereotype.Component;

import com.ep.backend.entities.successStory.IbdpResult;

@Component
public class IbdpResultDtoToEntity {
	

	public IbdpResult dtoToIbdpResult(IbdpResultDto ibdpResultDto) {
		IbdpResult ibdpResult = new IbdpResult();
		
		ibdpResult.setStudentName(ibdpResultDto.getStudentName());
		ibdpResult.setSchool_Name(ibdpResultDto.getSchool_Name());
		ibdpResult.setYear(ibdpResultDto.getYear());
		ibdpResult.setLevel(ibdpResultDto.getLevel());
		ibdpResult.setScore(ibdpResultDto.getScore());
		ibdpResult.setIA_Score(ibdpResultDto.getIA_Score());
		ibdpResult.setStatus(ibdpResultDto.isStatus());
		return ibdpResult;
	}
	
	public IbdpResultDto ibpdResultToDto (IbdpResult ibdpResult) {
		IbdpResultDto ibdpResultDto = new IbdpResultDto();
		ibdpResultDto.setStudentName(ibdpResult.getStudentName());
		ibdpResultDto.setSchool_Name(ibdpResult.getSchool_Name());
		ibdpResultDto.setYear(ibdpResult.getYear());
		ibdpResultDto.setLevel(ibdpResult.getLevel());
		ibdpResultDto.setScore(ibdpResult.getScore());
		ibdpResultDto.setIA_Score(ibdpResult.getIA_Score());
		ibdpResultDto.setStatus(ibdpResult.isStatus());
		return ibdpResultDto;
	}
}
