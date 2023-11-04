package com.ep.backend.dto.successStory;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CompetitionResultsDto {

	private String studentName;
	private String schoolName;
	private String competition;
	private String score1;
	boolean status;
	
	public CompetitionResultsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
