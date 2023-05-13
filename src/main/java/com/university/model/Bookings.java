package com.university.model;

import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name="bookings")
@IdClass(BookingId.class)
public class Bookings {

	@Id
	@Column(name="studentId")
	private int studentId;
	
	@Id
	@Column(name="stopName")
	private String stopName;
	
	@Id
	@Column(name="timeSlot")
	private java.sql.Time timeSlot;

	public Bookings(int studentId,String stopName, Time timeSlot) {
		super();
		this.studentId = studentId;
		this.stopName = stopName;
		this.timeSlot = timeSlot;
		
	}

	public Bookings() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public java.sql.Time getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(java.sql.Time timeSlot) {
		this.timeSlot = timeSlot;
	}

	public String getStopName() {
		return stopName;
	}

	public void setStopName(String stopName) {
		this.stopName = stopName;
	}
	
	
}
