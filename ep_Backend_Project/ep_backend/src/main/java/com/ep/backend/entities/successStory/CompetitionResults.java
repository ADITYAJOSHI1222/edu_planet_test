package com.ep.backend.entities.successStory;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class CompetitionResults {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int studentId;
	
	private String studentName;
	private String schoolName;
	private String competition;
	private String score1;
	boolean status;
	
	public CompetitionResults() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

