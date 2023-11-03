package com.ep.backend.controllers;

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
import com.ep.backend.dto.MYP_ResultsDto;
import com.ep.backend.repositories.MYP_ResultsRepo;
import com.ep.backend.service.MYP_ResultsService;


@CrossOrigin
@RestController
@RequestMapping("/api/myp_results")
@Component
public class MYP_ResultsController {

	@Autowired
	MYP_ResultsService myp_resultsService;
	
	@Autowired
	MYP_ResultsRepo myp_resultsRepo;
	
	@PostMapping
	public ResponseEntity<MYP_ResultsDto> aaddMYP_Result(@RequestBody MYP_ResultsDto myp_resultsDto) {
		
		MYP_ResultsDto addedMYP_Result = this.myp_resultsService.addMYP_Result(myp_resultsDto);
		return ResponseEntity.ok(addedMYP_Result);
	}
	
	@GetMapping
	public ResponseEntity<List<MYP_ResultsDto>> getAllMYP_Results() {
		return ResponseEntity.ok(this.myp_resultsService.getAllMYP_Results());
	}
	
	@GetMapping("/{studentName}")
	public ResponseEntity<?> findByStudentName(@PathVariable String studentName) {
		try {
			String decodedStudentName = URLDecoder.decode(studentName, StandardCharsets.UTF_8.toString());
			
			List<MYP_ResultsDto> myp_resultdsDtoList =this.myp_resultsService.findByStudentName(decodedStudentName);
			
			if (myp_resultdsDtoList != null && !myp_resultdsDtoList.isEmpty()) {
				return ResponseEntity.ok(myp_resultdsDtoList);
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}
		catch (UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error decoding URL parameter");
		}
	}
	
//	@GetMapping("/studentName")
//	public ResponseEntity<?> findByStudentName(@PathVariable String studentName) {
//	    try {
//	        String decodedStudentName = URLDecoder.decode(studentName, StandardCharsets.UTF_8.toString());
//	        
//	        // You should call a service method here, not itself.
//	        // Example: List<MYP_ResultsDto> myp_resultsDtoList = myp_resultsService.findByStudentName(decodedStudentName);
//	        List<MYP_ResultsDto> myp_resultsDtoList = this.myp_resultsService.findByStudentName(decodedStudentName);
//	        if (myp_resultsDtoList != null && !myp_resultsDtoList.isEmpty()) {
//	            return ResponseEntity.ok(myp_resultsDtoList);
//	        }
//	        else {
//	            return ResponseEntity.notFound().build();
//	        }
//	    }
//	    catch (UnsupportedEncodingException e) {
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error decoding URL parameter");
//	    }
//	}

	
	@DeleteMapping()
	public ResponseEntity<Map<String, String>> deleteAllMYP_Results() {
		List<MYP_ResultsDto> deletedAllMYP_Results = this.myp_resultsService.getAllMYP_Results();
		
		this.myp_resultsService.deleteAllMYP_Results();
		return ResponseEntity.ok(Map.of("message","Delete MYP_Results Successfully"));
	}
	
	@DeleteMapping("/{studentName}")
	public ResponseEntity<?> deleteSingleMYP_Result(@PathVariable String studentName) {
		boolean success = this.myp_resultsService.deleteSingleMYP_Result(studentName);
		
		if (success) {
			return ResponseEntity.ok(Map.of("message", "MYP_Result Deleted Successfully"));
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","MYP_Result not Available"));
		}
	}
	
	@PutMapping("/{studentName}")
	public ResponseEntity<MYP_ResultsDto> updateMYP_Result(
			@PathVariable String studentName,
			@RequestBody MYP_ResultsDto myp_resultDto) throws ResourceNotFoundException {
			
		MYP_ResultsDto updatedMYP_Result = (MYP_ResultsDto) this.myp_resultsService.updateMYP_Results(myp_resultDto, studentName);
		
		if(updatedMYP_Result != null) {
			return ResponseEntity.ok(updatedMYP_Result);
		}
		else {
			return ResponseEntity.notFound().build();
		}	
	}
}
