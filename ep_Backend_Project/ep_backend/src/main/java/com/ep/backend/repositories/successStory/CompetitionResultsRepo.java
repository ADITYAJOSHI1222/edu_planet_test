package com.ep.backend.repositories.successStory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ep.backend.entities.successStory.CompetitionResults;



@Repository
public interface CompetitionResultsRepo extends JpaRepository<CompetitionResults , Integer>{

	Optional<CompetitionResults> findByStudentName(String studentName);
	CompetitionResults deleteByStudentName(String studentName);
}
