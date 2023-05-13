package com.university.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.model.Bookings;
import com.university.model.Route;
import com.university.model.Schedule;
import com.university.repository.bookingDetails;
import com.university.repository.routeDetails;
import com.university.repository.scheduleDetails;

@Service
public class scheduleService {
	
	@Autowired 
	private scheduleDetails schedulerepo;

	
	@Autowired
	private routeDetails routerepo;
	
	@Autowired
	private bookingDetails bookingrepo;
	
//	@Autowired
//	private Bookings booking;
	
	public List<Schedule> getAvailableShuttles() {
		return schedulerepo.findAll();
		
	}
	
	public List<Route> getRouteDetails(){
		return routerepo.findAll();
	}

	public List<java.sql.Time> getScheduleTimings(String routeName) {
		List<Schedule> objects=schedulerepo.findAllBystopName(routeName);
		ArrayList<java.sql.Time> result = new ArrayList<java.sql.Time>();
		for(int i=0;i<objects.size();i++) {
			result.add(objects.get(i).getTimeSlot());
		}
		return result;
		
	}

	public Bookings scheduleRide(Bookings booking) {
		return bookingrepo.save(booking);
	}

	public List<Bookings> getBookingDetails(int id) {
		return bookingrepo.findAllBystudentId(id);
	}

}
