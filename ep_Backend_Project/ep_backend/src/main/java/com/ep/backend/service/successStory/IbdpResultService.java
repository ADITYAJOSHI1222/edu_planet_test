package com.ep.backend.service.successStory;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ep.backend.Exception.ResourceNotFoundException;
import com.ep.backend.dto.successStory.IbdpResultDto;


@Service
public interface IbdpResultService {

	IbdpResultDto addIbdpResult(IbdpResultDto ibdpResultDto);
	List<IbdpResultDto> getAllIbdpResults();
	List<IbdpResultDto> findByStudentName(String studentName);
	IbdpResultDto updateIbdpResult(IbdpResultDto ibdpResultDto, String studentName) throws ResourceNotFoundException;
    IbdpResultDto patchIbdpResult(IbdpResultDto ibdpResultDto, String studentName) throws ResourceNotFoundException;
	boolean deleteSingleIbdpResult(String studentName);
	boolean deleteAllIbdpResult();
}
