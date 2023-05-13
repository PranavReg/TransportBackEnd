package com.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.university.model.Bookings;
import com.university.model.Route;
import com.university.service.scheduleService;

@RestController
public class ScheduleController {
	
	@Autowired
	private scheduleService schedule;
	
	
	@GetMapping("/timings")
	@CrossOrigin()
	public List<java.sql.Time> getSchdeuleTimings(@RequestParam String routeName) {
	return schedule.getScheduleTimings(routeName);
	}
	
	@GetMapping("/shuttleRoutes")
	@CrossOrigin()
	public List<Route> getRouteDetails() {
		return schedule.getRouteDetails();
	}
	
	@PostMapping("/scheduleRide")
	@CrossOrigin()
	public ResponseEntity<Object> scheduleRide(@RequestBody Bookings booking) {
		schedule.scheduleRide(booking);
		return ResponseEntity.status(201).body(booking);
		
	}
	
	@GetMapping("/mybookings")
	@CrossOrigin()
	public List<Bookings> getBookingDetails(@RequestParam int studentId) {
		return schedule.getBookingDetails(studentId);
	}
}
