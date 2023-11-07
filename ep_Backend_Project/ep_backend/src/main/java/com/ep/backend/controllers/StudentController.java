package com.ep.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ep.backend.dto.Response;
import com.ep.backend.dto.Credential;
import com.ep.backend.dto.LoginMesage;
import com.ep.backend.dto.StudentDto;
import com.ep.backend.service.StudentService;
import com.ep.backend.services.Impl.ServiceImpl;

import lombok.AllArgsConstructor;
@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/studentAPI")
public class StudentController {
	 @Autowired
	    private ServiceImpl studentService;
	 
//	 student sign-up
//	  @PostMapping(path = "/signin")
//	  public ResponseEntity<?> signIn(@RequestBody Credential cred)
//	    {
//	     StudentDto studentDto =  studentService.findBystudentsEmailIdAndPassword(cred);
//	     if (studentDto == null)
//	    	 return Response.error("user not found");
//			return Response.success(studentDto);
//	    }
//	 
	 
	 
	 
	 

//    @PostMapping(path = "/login")
//	    public ResponseEntity<?> loginEmployee(@RequestBody StudentDto studentDto)
//	    {
//	        LoginMesage loginResponse = studentService.loginEmployee(studentDto);
//	        return ResponseEntity.ok(loginResponse);
//	    }

	  
	//create  student
	 @PostMapping
	    public ResponseEntity<StudentDto> saveStudent(@RequestBody StudentDto studentDto) {
		//Service will call the create student and pass studentdto 
	        StudentDto savedStudent = studentService.saveStudent(studentDto);
	        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
	    }
// update student
	    @PutMapping("/{studentId}")
	    public ResponseEntity<StudentDto> updateStudent(@PathVariable("studentId") String student_id,
	                                                    @RequestBody StudentDto studentDto) {
	        studentDto.setStudent_id(student_id);
	      //Service will call the create student and pass studentdto 		
			StudentDto updatedStudent = studentService.updateStudent(studentDto, student_id);
			return new ResponseEntity<>(updatedStudent , HttpStatus.OK);
			
	    }
	    
//		delete

	    @DeleteMapping("/{studentId}")
	    public ResponseEntity<Void> deleteStudent(@PathVariable String studentId) {
	        studentService.deleteStudent(studentId);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

//		get by email
	    @GetMapping("/{email}")
	    public ResponseEntity<StudentDto> findStudentByEmail(@PathVariable("email") String studentsEmailId)
				 {
	    	return new ResponseEntity<>( studentService.findStudentByEmail(studentsEmailId), HttpStatus.OK);
	    }
	    
//	    get student by id
	    @GetMapping("/id/{studentId}")
		public ResponseEntity<StudentDto> getStudent(
				@PathVariable("studentId") String student_id){
			return new ResponseEntity<>( studentService.getStudentById(student_id), HttpStatus.OK);
			
		}
	    
	    
//		get all student
	    @GetMapping
	    public ResponseEntity<List<StudentDto>> getAllStudents() {
	        List<StudentDto> students = studentService.getAllStudents();
	        return new ResponseEntity<>(students, HttpStatus.OK);
	    }

//	    @PostMapping("/reset-password/{email}")
//	    public ResponseEntity<Void> resetPassword(
//	    		@PathVariable("email") String  studentsEmailId,
//	    		@RequestBody Credential credentials) {
//	        studentService.resetPassword(credentials);
//	        return new ResponseEntity<>(HttpStatus.OK);
//	    }
	    
//		search user
		@GetMapping("/search/{keyword}")
		public ResponseEntity<List<StudentDto>> searchStudent(
				@PathVariable String keyword){
			return new ResponseEntity<>( studentService.searchStudent(keyword), HttpStatus.OK);
			
		}
}
