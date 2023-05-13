package com.university.model;

import java.sql.Time;


public class BookingId {
	

	private int studentId;

	private String stopName;
	
	private java.sql.Time timeSlot;

	public BookingId(int studentId, Time timeSlot, String stopName) {
		super();
		this.studentId = studentId;
		this.timeSlot = timeSlot;
		this.stopName = stopName;
	}

	public BookingId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
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
	
	
}
