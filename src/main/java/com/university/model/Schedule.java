package com.university.model;

import java.sql.Time;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="schedule")
public class Schedule {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="stopName")
	private String stopName;
	

	@Column(name="timeSlot")
	private java.sql.Time timeSlot;
	
	public Schedule(int id,String stopName, Time timeSlot) {
		super();
		this.id=id;
		this.stopName = stopName;
		this.timeSlot = timeSlot;
	}

	public Schedule() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getStopName() {
		return stopName;
	}

	public void setStopName(String stopName) {
		this.stopName = stopName;
	}

	public java.sql.Time getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(java.sql.Time timeSlot) {
		this.timeSlot = timeSlot;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}
