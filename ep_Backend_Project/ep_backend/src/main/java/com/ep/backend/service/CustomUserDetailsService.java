package com.ep.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ep.backend.entities.Student;
import com.ep.backend.repositories.StudentRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
    private StudentRepo repo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Student student =  repo.findBystudentsEmailId(username).orElseThrow(() -> new UsernameNotFoundException("User with given email not found !!")); 
		
		return student;
	}

}
