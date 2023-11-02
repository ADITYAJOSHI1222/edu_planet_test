package com.ep.backend.services.Impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ep.backend.Exception.ResourceNotFoundException;
import com.ep.backend.dto.Credential;
import com.ep.backend.dto.EntityDtoConvertor;
import com.ep.backend.dto.LoginMesage;
import com.ep.backend.dto.StudentDto;
import com.ep.backend.entities.Role;
import com.ep.backend.entities.Student;
import com.ep.backend.repositories.RoleRepository;
import com.ep.backend.repositories.StudentRepo;
import com.ep.backend.service.StudentService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServiceImpl implements StudentService {
	@Autowired
	private StudentRepo studentRepository;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private EntityDtoConvertor entityDtoConvertor;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Value("${normal.role.id}")
	private String normalRoleId;

	@Autowired
	private RoleRepository roleRepository;

	private Logger logger = LoggerFactory.getLogger(ServiceImpl.class);

//	public Optional<Student> signIn(Student student) {
//
//		return studentRepository.findBystudentsEmailIdAndPassword(student.getStudentsEmailId(), student.getPassword());
//	}

//	public StudentDto findBystudentsEmailIdAndPassword(Credential cred) {
//		Student dbStudent = studentRepository.findBystudentsEmailId(cred.getEmail());
//		String rawPassword = cred.getPassword();
//		if (dbStudent != null && passwordEncoder.matches(rawPassword, dbStudent.getPassword())) {
//			StudentDto result = entityDtoConvertor.toStudDto(dbStudent);
//			result.setPassword("********");
//			return result;
//		}
//
//		return null;
//	}

//    public LoginMesage  loginEmployee(Credential cred) {
//	        String msg = "";
//	        Student  student =  studentRepository.findBystudentsEmailId(cred.getEmail());
//	        if ( student != null) {
//	            String password = cred.getPassword();
//	            String encodedPassword =  student.getPassword();
//	            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
//	            if (isPwdRight) {
//	                Optional< Student> user =studentRepository.findBystudentsEmailIdAndPassword(cred.getEmail(), encodedPassword);
//	                if (user.isPresent()) {
//	                    return new LoginMesage("Login Success", true);
//	                } else {
//	                    return new LoginMesage("Login Failed", false);
//	                }
//	            } else {
//	                return new LoginMesage("password Not Match", false);
//	            }
//	        }else {
//	            return new LoginMesage("Email not exits", false);
//	        }
//	    }

//	create student
	@Override
	public StudentDto saveStudent(StudentDto studentDto) {

//			 inside this we want to generate unique id in string format
		String studentId = UUID.randomUUID().toString();
		studentDto.setStudent_id(studentId);

// password encoder
		studentDto.setPassword(passwordEncoder.encode(studentDto.getPassword()));

// dto->entity		
		Student student = entityDtoConvertor.toStudentEntity(studentDto);
		
//fetch role of normal and set it to user	
		Role role=roleRepository.findById(normalRoleId).get();
		student.getRoles().add(role);
		
		Student savedStudent = studentRepository.save(student);

		return entityDtoConvertor.toStudDto(savedStudent);
	}

// update student
	@Override
	public StudentDto updateStudent(StudentDto studentDto, String studentId) {
		// Assuming student_id is already set in studentDto
		Student existingStudent = studentRepository.findById(studentId)
				.orElseThrow(() -> new RuntimeException("Student not found"));

		// Update student fields
		existingStudent.setStudentsName(studentDto.getStudentsName());
		existingStudent.setStudents_number(studentDto.getStudents_number());
		existingStudent.setStudentsEmailId(studentDto.getStudentsEmailId());
		existingStudent.setParentsName(studentDto.getParentsName());
		existingStudent.setParents_number(studentDto.getParents_number());
		existingStudent.setParents_emailId(studentDto.getParents_emailId());
		existingStudent.setDate(studentDto.getDate());
		existingStudent.setGrade(studentDto.getGrade());

		Student updatedStudent = studentRepository.save(existingStudent);

		return entityDtoConvertor.toStudDto(updatedStudent);
	}

	@Override
	public void deleteStudent(String studentId) {
		studentRepository.deleteById(studentId);
	}

	@Override
	public List<StudentDto> getAllStudents() {
		List<Student> students = studentRepository.findAll();
		List<StudentDto> studentDto = students.stream().map(student -> entityDtoConvertor.toStudDto(student))
				.collect(Collectors.toList());
		return studentDto;
	}

//	@Override
//	public void resetPassword(Credential credentials) {
//		String email = credentials.getEmail();
//		String newPassword = credentials.getPassword();
////		String encodedPassword = passwordEncoder.encode(newPassword);
//
//		Student student = studentRepository.findBystudentsEmailId(email);
//		if (student != null) {
//			student.setPassword(newPassword);
//			studentRepository.save(student);
//		} else {
//			throw new IllegalArgumentException("Student not found with email: " + email);
//		}
//	}

	@Override
	public List<StudentDto> searchStudent(String keyword) {
		List<Student> students = studentRepository.findBystudentsNameContaining(keyword);
		List<StudentDto> studentDto = students.stream().map(student -> entityDtoConvertor.toStudDto(student))
				.collect(Collectors.toList());
		return studentDto;
	}

	@Override
	public StudentDto getStudentById(String studentId) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new RuntimeException("Student with this Id not found"));
//		in repo there is a find by id . when we give id it will give optional containing user or else will give exception	
//			and we want to return dto but we have user

		return entityDtoConvertor.toStudDto(student);
	}

	@Override
	public StudentDto findStudentByEmail(String email) {
		Student student = studentRepository.findBystudentsEmailId(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with given email id !!"));
		return entityDtoConvertor.toStudDto(student);
	}

	@Override
	public Optional<Student> findStudentByEmailOptional(String email) {
		// TODO Auto-generated method stub
		return studentRepository.findBystudentsEmailId(email);
	}

}
