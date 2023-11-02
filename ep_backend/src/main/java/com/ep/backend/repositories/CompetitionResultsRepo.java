package com.ep.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ep.backend.entities.CompetitionResults;



@Repository
public interface CompetitionResultsRepo extends JpaRepository<CompetitionResults , Integer>{

	Optional<CompetitionResults> findByStudentName(String studentName);
	CompetitionResults deleteByStudentName(String studentName);
}
