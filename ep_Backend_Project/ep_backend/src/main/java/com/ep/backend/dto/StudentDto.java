package com.ep.backend.dto;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.ep.backend.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentDto {
	private String student_id;
	
	
	private String studentsName;
	
	
	private long students_number;
	 
	
	private String studentsEmailId;


	private String parentsName;
	
	
    private long parents_number;
	
	
	private String parents_emailId;
	
	private Set<RoleDto> roles = new HashSet<>();
	
	private Date date;
	
	private int grade;
	  
    private String password;
	 

}
