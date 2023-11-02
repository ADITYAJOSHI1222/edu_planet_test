package com.ep.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ep.backend.entities.Student;

public interface StudentRepo extends JpaRepository<Student, String>{
	
	Optional<Student>  findBystudentsEmailId(String email);
	
	 Optional<Student> findBystudentsEmailIdAndPassword(String email, String password);
	 
//		search
		List<Student> findBystudentsNameContaining(String keyword);
	
}
