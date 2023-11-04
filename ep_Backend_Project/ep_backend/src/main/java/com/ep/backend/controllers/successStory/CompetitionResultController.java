package com.ep.backend.controllers.successStory;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ep.backend.Exception.ResourceNotFoundException;
import com.ep.backend.dto.successStory.CompetitionResultsDto;
import com.ep.backend.repositories.successStory.CompetitionResultsRepo;
import com.ep.backend.service.successStory.CompetitionResultsService;


@CrossOrigin
@RestController
@RequestMapping("/api/competitionResults")
@Component
public class CompetitionResultController {

	@Autowired
	CompetitionResultsService competitionResultService;
	
	@Autowired
	CompetitionResultsRepo competitionResultsRepo;
	
	@PostMapping
	public ResponseEntity<CompetitionResultsDto> addCompetitionResult(@RequestBody CompetitionResultsDto competitionResultsDto) {
		
		CompetitionResultsDto addCompetitionResult = this.competitionResultService.addCompetitionResult(competitionResultsDto);
		return ResponseEntity.ok(addCompetitionResult);
	}
	
	@GetMapping
	public ResponseEntity<List<CompetitionResultsDto>> getAllCompetitionResults() {
		return ResponseEntity.ok(this.competitionResultService.getAllCompetitionResults());
	}
	
	@GetMapping("/{studentName}")
	public ResponseEntity<?> findByStudentName(@PathVariable String studentName) {
		try {
			String decodedStudentName = URLDecoder.decode(studentName, StandardCharsets.UTF_8.toString());
			
			List<CompetitionResultsDto> competitionResultDtoList = this.competitionResultService.findByStudentName(decodedStudentName);
			
			if(competitionResultDtoList != null && !competitionResultDtoList.isEmpty()) {
				return ResponseEntity.ok(competitionResultDtoList);
			}
			
			else {
				return ResponseEntity.notFound().build();
			}
		}
		catch (UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error decoding URL parameter");
		}
	}
	
	@DeleteMapping() 
	public ResponseEntity<Map<String,String>> deleteAllCompetitionResults() {
		List<CompetitionResultsDto> deletedAllCompetitionResults= this.competitionResultService.getAllCompetitionResults();
		
		this.competitionResultService.deleteAllCompetitionResults();
		return ResponseEntity.ok(Map.of("message","Delete CompetitionResult Successfully"));
	}
	
	@DeleteMapping("/{studentName}")
		public ResponseEntity<?> deleteSingleCompetitionResult(@PathVariable String studentName) {
			boolean success = this.competitionResultService.deleteSingleCompetitionResults(studentName);
			
			if(success) {
				return ResponseEntity.ok(Map.of("message","Competition Result Delete Successfully"));
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Competition Result not Available."));
			}
	}
	
	@PutMapping("/{studentName}")
	public ResponseEntity<CompetitionResultsDto> updateCompetitionResult(
			@PathVariable String studentName,
			@RequestBody CompetitionResultsDto competitionResultsDto) throws ResourceNotFoundException {
		
		CompetitionResultsDto updatedCompetitionResult = (CompetitionResultsDto) this.competitionResultService.updateCompetitionResults(competitionResultsDto, studentName);
		
		if(updatedCompetitionResult != null) {
			return ResponseEntity.ok(updatedCompetitionResult);
		}
		else {
			return ResponseEntity.notFound().build();	
			}
		}
	}


