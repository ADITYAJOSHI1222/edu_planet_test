package com.ep.backend.dto.successStory;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IGCSE_ResultsDto {

	private String studentName;
	private String Student_School;
	private int Year;
	private String Extented;
	private String Additional;
	private String International;
	boolean Status;
	
	public IGCSE_ResultsDto() {
		super();
	}

	}

