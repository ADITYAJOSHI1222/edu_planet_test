package com.ep.backend.service;

import java.util.List;
import java.util.Optional;

import com.ep.backend.dto.Credential;
import com.ep.backend.dto.LoginMesage;
import com.ep.backend.dto.StudentDto;
import com.ep.backend.entities.Student;

public interface StudentService {
	//create
	 StudentDto saveStudent(StudentDto studentDto);
	 
	//update
	    StudentDto updateStudent(StudentDto studentDto, String studentId);

	  //delete    
	    void deleteStudent(String studentId);
	    
//		get single user by email
	    StudentDto findStudentByEmail(String email);
	    
	  //get all user;
	    List<StudentDto> getAllStudents();
	    
// get student by id
	    StudentDto getStudentById(String studentId);
	    
	    
// reset password
//	    void resetPassword(Credential credentials);
	    
//	    LoginMesage loginEmployee(StudentDto studentDto);
	    
	  //search user
		
		List<StudentDto> searchStudent(String keyword); 
		
		  //other user specific features

	    Optional<Student> findStudentByEmailOptional(String email);
}
