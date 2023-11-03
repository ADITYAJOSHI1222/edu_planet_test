package com.ep.backend.controllers;

import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ep.backend.Exception.BadApiRequestException;
import com.ep.backend.Security.JwtHelper;
import com.ep.backend.dto.JwtRequest;
import com.ep.backend.dto.JwtResponse;
import com.ep.backend.dto.StudentDto;
import com.ep.backend.service.StudentService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	 @Autowired
    private UserDetailsService userDetailsService;
	 
	 @Autowired
	 private ModelMapper mapper;
	 
	 @Autowired
	  private AuthenticationManager manager;
	 
	 @Autowired
	    private StudentService studentService;
	 
	 @Autowired
	    private JwtHelper helper;
	 
	 
	 private Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	 
//	create endpoint
	 @PostMapping("/login")
	  public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
		  this.doAuthenticate(request.getEmail(),request.getPassword());
		        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		        String token = this.helper.generateToken(userDetails);
		        StudentDto studentDto = mapper.map(userDetails, StudentDto.class);
		        JwtResponse response = JwtResponse.builder()
		                .jwtToken(token)
		                .user(studentDto).build();
		        return new ResponseEntity<>(response, HttpStatus.OK);
	 
	 
	  } 
	 
private void doAuthenticate(String email, String password) {
	 UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
     try {
         manager.authenticate(authentication);
     } catch (BadCredentialsException e) {
         throw new BadApiRequestException(" Invalid Username or Password  !!");
     }
	
}





//	this will show whch user is logged in
	@GetMapping("/current")
	public ResponseEntity<StudentDto> getCurrentUser(Principal principal){
		String name = principal.getName();		
		return new ResponseEntity<>(mapper.map(userDetailsService.loadUserByUsername(name), StudentDto.class), HttpStatus.OK);
    }

}
