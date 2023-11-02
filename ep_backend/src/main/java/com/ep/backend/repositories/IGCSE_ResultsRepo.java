package com.ep.backend.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ep.backend.entities.IGCSE_Results;



@Repository
public interface IGCSE_ResultsRepo extends JpaRepository<IGCSE_Results, Integer> {
	
	List<IGCSE_Results> findByStudentName(String studentName);
	IGCSE_Results deleteByStudentName(String studentName);
}
