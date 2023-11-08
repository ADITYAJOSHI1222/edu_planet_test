package com.ep.backend.entities.aboutUs;

import java.sql.Date;
import java.util.Set;

import com.ep.backend.entities.Role;
import com.ep.backend.entities.Student;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity 
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class InstituteInfo {
	@Id	
private String 	aboutUsInstitutetext;
	
	private String 	aboutUsInstituteBox1Title;
	
	private String 	aboutUsInstituteBox1Description;
	
    private String 	aboutUsInstituteBox2Title;
	
	private String 	aboutUsInstituteBox2Description;
	
    private String 	aboutUsInstituteBox3Title;
	
	private String 	aboutUsInstituteBox3Description;
	
    private String 	aboutUsInstituteBox4Title;
	
	private String 	aboutUsInstituteBox4Description;
	
	@OneToOne
    @JoinColumn(name = "aboutUsBannnerHeading")
    private AboutUs aboutUs;
}
