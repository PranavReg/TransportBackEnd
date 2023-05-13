package com.university.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import com.university.controller.ScheduleController;
import com.university.model.Bookings;
import com.university.model.Route;
import com.university.service.scheduleService;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
@ContextConfiguration(locations =  {"classpath*:/spring/test-context.xml"})
public class ScheduleControllerTest {
	
	@Mock
	private scheduleService scheduleService;
	
	@InjectMocks
	private ScheduleController controller;
	
	private Bookings booking;
	private Route route;
	
	@Test
	public void test_getSchdeuleTimings() {
		List<java.sql.Time> timings=List.of(java.sql.Time.valueOf("18:00:00"),java.sql.Time.valueOf("19:00:00"));
		when(scheduleService.getScheduleTimings("routeName")).thenReturn(timings);
		
		List<java.sql.Time> fetchedTimings=controller.getSchdeuleTimings("routeName");
		assertThat(fetchedTimings).isEqualTo(timings);
	}
	
	@Test
	public void test_getRouteDetails() {
		route=new Route(1,"stopName1","stopName2","stopName3");
		when(scheduleService.getRouteDetails()).thenReturn(List.of(route));
		
		List<Route> fetchedRoutes=controller.getRouteDetails();
		assertThat(fetchedRoutes).isEqualTo(List.of(route));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void test_booking_confirm() throws Exception {
		booking=new Bookings(1234,"stopName1",java.sql.Time.valueOf("18:00:00"));
			
		ResponseEntity<Object> saveBooking=controller.scheduleRide(booking);

		assertThat(saveBooking.getStatusCodeValue()).isEqualTo(201);
		assertThat(saveBooking.getBody()).isEqualTo(booking);
		
	}

	@Test
	public void test_getBookingDetails() {
		booking=new Bookings(1234,"stopName1",java.sql.Time.valueOf("18:00:00"));
		when(scheduleService.getBookingDetails(1)).thenReturn(List.of(booking));
		
		List<Bookings> fetchBookings=controller.getBookingDetails(1);
		assertThat(fetchBookings).isEqualTo(List.of(booking));
	}
	
}
