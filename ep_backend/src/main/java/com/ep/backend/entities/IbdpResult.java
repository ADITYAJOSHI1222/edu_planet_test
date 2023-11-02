package com.ep.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="Ibdp_Result")
public class IbdpResult {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int Student_id;
	
	@Column(name = "studentName")
    private String studentName;
	private String School_Name;
	private int Year;
	private String Level;
    //@Type(type = "fraction")
	private String Score;
    //@Type(type = "fraction", value = null)

	private String IA_Score;
	private boolean Status;
    
	
	public IbdpResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public IbdpResult(int student_id, String studentName, String school_Name, int year, String level, String score,
			String iA_Score, boolean status) {
		super();
		Student_id = student_id;
		this.studentName = studentName;
		School_Name = school_Name;
		Year = year;
		Level = level;
		Score = score;
		IA_Score = iA_Score;
		Status = status;
	}



	public int getStudent_id() {
		return Student_id;
	}

	public void setStudent_id(int student_id) {
		Student_id = student_id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getSchool_Name() {
		return School_Name;
	}

	public void setSchool_Name(String school_Name) {
		School_Name = school_Name;
	}

	public int getYear() {
		return Year;
	}

	public void setYear(int year) {
		Year = year;
	}

	public String getLevel() {
		return Level;
	}

	public void setLevel(String level) {
		Level = level;
	}

	public String getScore() {
		return Score;
	}

	public void setScore(String score) {
		Score = score;
	}

	public String getIA_Score() {
		return IA_Score;
	}

	public void setIA_Score(String iA_Score) {
		IA_Score = iA_Score;
	}

	public boolean isStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}

	
	
}