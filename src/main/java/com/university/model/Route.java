package com.university.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="route")
public class Route {
	
	@Id
	@Column(name="RouteId")
	private int RouteId;
	
	@Column(name="StopName1")
	private String StopName1;
	
	@Column(name="StopName2")
	private String StopName2;
	
	@Column(name="StopName3")
	private String StopName3;

	public Route(int routeId, String stopName1, String stopName2, String stopName3) {
		super();
		RouteId = routeId;
		StopName1 = stopName1;
		StopName2 = stopName2;
		StopName3 = stopName3;
	}

	public Route() {
		super();
	}

	public int getRouteId() {
		return RouteId;
	}

	public void setRouteId(int routeId) {
		RouteId = routeId;
	}

	public String getStopName1() {
		return StopName1;
	}

	public void setStopName1(String stopName1) {
		StopName1 = stopName1;
	}

	public String getStopName2() {
		return StopName2;
	}

	public void setStopName2(String stopName2) {
		StopName2 = stopName2;
	}

	public String getStopName3() {
		return StopName3;
	}

	public void setStopName3(String stopName3) {
		StopName3 = stopName3;
	}
	
	
	
}
