package com.ep.backend;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ep.backend.entities.Role;
import com.ep.backend.entities.Student;
import com.ep.backend.repositories.RoleRepository;
import com.ep.backend.repositories.StudentRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class EpBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EpBackendApplication.class, args);
	}
	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;
    
	 @Autowired
	    private RoleRepository repository;
	    @Value("${normal.role.id}")
	    private String role_normal_id;
	    @Value("${admin.role.id}")
	    private String role_admin_id;

	    @Autowired
	    private StudentRepo repo;
	@Override
	public void run(String... args) throws Exception {
		System.out.println(passwordEncoder.encode( "student_password2"));
		
		   try {

	            Role role_admin = Role.builder().roleId(role_admin_id).roleName("ROLE_ADMIN").build();
	            Role role_normal = Role.builder().roleId(role_normal_id).roleName("ROLE_NORMAL").build();


//	            Student adminUser = Role.builder()
//	                    .name("admin")
//	                    .email("admin@gmail.com")
//	                    .password(passwordEncoder.encode("admin123"))
//	                    .gender("Male")
//	                    .imageName("default.png")
//	                    .roles(Set.of(role_admin, role_normal))
//	                    .userId(UUID.randomUUID().toString())
//	                    .about("I am admin User")
//	                    .build();
//
//	            Student normalUser = Role.builder()
//	                    .name("durgesh")
//	                    .email("durgesh@gmail.com")
//	                    .password(passwordEncoder.encode("durgesh123"))
//	                    .gender("Male")
//	                    .imageName("default.png")
//	                    .roles(Set.of(role_normal))
//	                    .userId(UUID.randomUUID().toString())
//	                    .about("I am Normal User")
//	                    .build();

	            repository.save(role_admin);
	            repository.save(role_normal);


//	            repo.save(adminUser);
//	            repo.save(normalUser);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
