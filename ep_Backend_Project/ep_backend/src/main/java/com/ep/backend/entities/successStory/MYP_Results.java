package com.ep.backend.entities.successStory;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class MYP_Results {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Student_id;
	
	@Column(name = "studentName")
    private String studentName;
	
	private String School_Name;
	private int Year;
	private String Levels;
	private String Score1;
	boolean Status;
	
	public MYP_Results() {
		super();
	}
}

