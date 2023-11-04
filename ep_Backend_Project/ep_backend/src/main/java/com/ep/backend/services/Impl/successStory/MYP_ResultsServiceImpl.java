package com.ep.backend.services.Impl.successStory;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ep.backend.Exception.ResourceNotFoundException;
import com.ep.backend.dto.successStory.MYP_ResultsDto;
import com.ep.backend.dto.successStory.MYP_ResultsDtoToEntity;
import com.ep.backend.entities.successStory.MYP_Results;
import com.ep.backend.repositories.successStory.MYP_ResultsRepo;
import com.ep.backend.service.successStory.MYP_ResultsService;


@Service
public class MYP_ResultsServiceImpl implements MYP_ResultsService{

	private final MYP_ResultsRepo myp_resultsRepo;
	private final MYP_ResultsDtoToEntity myp_resultsDtoToEntity;
	
	@Autowired
	public MYP_ResultsServiceImpl(MYP_ResultsRepo myp_resultsRepo, MYP_ResultsDtoToEntity myp_resultsDtoToEntity) {
		super();
		this.myp_resultsRepo = myp_resultsRepo;
		this.myp_resultsDtoToEntity = myp_resultsDtoToEntity;
	}
	
    @Override
	public MYP_ResultsDto addMYP_Result(MYP_ResultsDto myp_resultsDto) {
		
		MYP_Results myp_result = myp_resultsDtoToEntity.dtoToMYP_Results(myp_resultsDto);
		myp_result = myp_resultsRepo.save(myp_result);
		myp_resultsDto = myp_resultsDtoToEntity.myp_resultsToDto(myp_result);
		return myp_resultsDto;
	}

	@Override
	public List<MYP_ResultsDto> getAllMYP_Results() {
		List<MYP_Results> myp_results = this.myp_resultsRepo.findAll();
		List<MYP_ResultsDto> myp_resultsDto = myp_results
			    .stream()
			    .map(result -> this.myp_resultsDtoToEntity.myp_resultsToDto(result))
			    .collect(Collectors.toList());
		return myp_resultsDto;
	}
	

	@Override
	public 	List<MYP_ResultsDto> findByStudentName(String studentName) {
		Optional<MYP_Results> myp_results = this.myp_resultsRepo.findByStudentName(studentName);
		List<MYP_ResultsDto> myp_resultsDto = myp_results.stream().map(myp_result -> this.myp_resultsDtoToEntity.myp_resultsToDto(myp_result)).collect(Collectors.toList());
		return myp_resultsDto;
		
	}		

	@Override
	public boolean deleteAllMYP_Results() {

		List<MYP_Results> myp_resultToDelete = this.myp_resultsRepo.findAll();
		if(myp_resultToDelete.isEmpty()) {
			return false;
		}
		else this.myp_resultsRepo.deleteAll(myp_resultToDelete);
		return true;
	}
@Override
public boolean deleteSingleMYP_Result(String studentName) {
	
	Optional<MYP_Results> myp_resultsToDelete = this.myp_resultsRepo.findByStudentName(studentName);
	
	if(!myp_resultsToDelete.isEmpty()) {
		MYP_Results myp_resultsToDelete1 = myp_resultsToDelete.get();
		this.myp_resultsRepo.delete(myp_resultsToDelete1);
		return true;
	}
	else {
		return false;
	}
}
	
	@Override
	public 	MYP_ResultsDto updateMYP_Results(MYP_ResultsDto myp_resultsDto, String studentName) throws ResourceNotFoundException {
		
		Optional<MYP_Results> myp_resultsOptional = this.myp_resultsRepo.findByStudentName(studentName);
		
		if(myp_resultsOptional.isPresent()) {
			MYP_Results myp_resultsToUpdate = myp_resultsOptional.get();
			
			myp_resultsToUpdate.setStudentName(myp_resultsDto.getStudentName());
			myp_resultsToUpdate.setSchool_Name(myp_resultsDto.getSchool_Name());
			myp_resultsToUpdate.setYear(myp_resultsDto.getYear());
			myp_resultsToUpdate.setLevels(myp_resultsDto.getLevels());
			myp_resultsToUpdate.setScore1(myp_resultsDto.getScore1());
			
			this.myp_resultsRepo.save(myp_resultsToUpdate);
			
			return myp_resultsDtoToEntity.myp_resultsToDto(myp_resultsToUpdate);
		}
		
		else {
			String errorMessage = "MYP_Results with studentName " +studentName + " is not available";
			
			throw new ResourceNotFoundException(errorMessage);
		}
	}
 
}
