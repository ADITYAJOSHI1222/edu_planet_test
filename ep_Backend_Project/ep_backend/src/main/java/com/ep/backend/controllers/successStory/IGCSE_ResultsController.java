package com.ep.backend.controllers.successStory;



import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
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
import com.ep.backend.dto.successStory.IGCSE_ResultsDto;
import com.ep.backend.service.successStory.IGCSE_ResultsService;



@CrossOrigin
@RestController
@RequestMapping("/api/igcse_results")
@Component
public class IGCSE_ResultsController {

	@Autowired
	private IGCSE_ResultsService igcse_resultsService;
	 
	@PostMapping
	public ResponseEntity<IGCSE_ResultsDto> addIGCSE_Results(@RequestBody IGCSE_ResultsDto igcse_resultsDto) {
		IGCSE_ResultsDto addIGCSE_Result = this.igcse_resultsService.addIGCSE_Results(igcse_resultsDto);
		return ResponseEntity.ok(addIGCSE_Result);
	}
	
	@GetMapping
	public ResponseEntity<List<IGCSE_ResultsDto>> getAllIGCSE_Results() {
		return ResponseEntity.ok(this.igcse_resultsService.getAllIGCSE_Results());
	}
	
//	@GetMapping("/student_name")
//	public ResponseEntity<?> findStudentByStudentName(@PathVariable String student_Name) {
//		try {
//			String decodeStudentName = URLDecoder.decode(student_Name, StandardCharsets.UTF_8.toString());
//			
//			List<IGCSE_ResultsDto> igcse_resultsDtoList = this.igcse_resultsService.findResultsByStudentName(decodeStudentName);
//		}
//	}
	
	@GetMapping("/{studentName}") // Define the URL path variable
	public ResponseEntity<?> findStudentByStudentName(@PathVariable String studentName) {
	    try {
	        // Decode the URL-encoded student name
	        String decodeStudentName = URLDecoder.decode(studentName, StandardCharsets.UTF_8.toString());

	        // Call a service method to retrieve the list of IGCSE results DTOs
	        List<IGCSE_ResultsDto> igcse_resultsDtoList = igcse_resultsService.findResultsByStudentName(decodeStudentName);

	        // Check if any results were found
	   //     if (!igcse_resultsDtoList.isEmpty()) {
	        if(igcse_resultsDtoList != null && !igcse_resultsDtoList.isEmpty()) {
	        	
	            // Return a ResponseEntity with a 200 OK status and the list of results
	            return ResponseEntity.ok(igcse_resultsDtoList);
	        } else {
	            // Return a ResponseEntity with a 404 Not Found status if no results were found
	            return ResponseEntity.notFound().build();
	        }
	    } catch (UnsupportedEncodingException e) {
	        // Handle the exception (e.g., log it or return an error response)
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error decoding student name");
	    }
	}
	
	@DeleteMapping()
	public List<IGCSE_ResultsDto> deleteAllIGCSE_Results(){
		List<IGCSE_ResultsDto> deleteAllIGCSE_Results = this.igcse_resultsService.getAllIGCSE_Results();
	//	boolean deletedAllIGCSE_Results = this.igcse_resultsService.deleteAllIGCSE_Results();
		this.igcse_resultsService.deleteAllIGCSE_Results();
		return deleteAllIGCSE_Results;
	}
	
	@DeleteMapping("/{StudentName}")
	public ResponseEntity<?> deleteIGCSE_Result(@PathVariable String studentName) {
	    boolean success = this.igcse_resultsService.deleteSingleIGCSE_Results(studentName);

	    if (success) {
	        return ResponseEntity.ok(Map.of("message", "IGCSE_Results Deleted Successfully"));
	    } else {
	        Map<String, String> errorResponse = new HashMap<>();
	        errorResponse.put("message", "IGCSE_Results not Available");

	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	    }
	}

	@PutMapping("/{StudentName}")
	public ResponseEntity<IGCSE_ResultsDto> updateIGCSE_Results(
			@PathVariable String studentName,
			@RequestBody IGCSE_ResultsDto igcse_resultsDto) throws ResourceNotFoundException {
		IGCSE_ResultsDto updatedIGCSE_Results = (IGCSE_ResultsDto) this.igcse_resultsService.updateIGCSE_Results(igcse_resultsDto, studentName);
		
		if(updatedIGCSE_Results != null) {
			return ResponseEntity.ok(updatedIGCSE_Results);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
