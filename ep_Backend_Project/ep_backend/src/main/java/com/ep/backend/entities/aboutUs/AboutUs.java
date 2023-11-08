package com.ep.backend.entities.aboutUs;

import java.util.ArrayList;
import java.util.List;
import com.ep.backend.entities.Student;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "aboutUs")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder                    
public class AboutUs {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String 	aboutUsBannnerHeading;
	
	private String 	aboutUsBannnerText;
	
	private String 	aboutUsInstituteHeading;
	
	 @OneToOne
	    private Student student;
	 
	 @OneToOne(mappedBy = "aboutUs", cascade = CascadeType.ALL)
	    private InstituteInfo instituteInfo;

	 
	 

}
















