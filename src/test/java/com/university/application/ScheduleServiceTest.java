package com.university.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;

import com.university.model.Bookings;
import com.university.model.Route;
import com.university.model.Schedule;
import com.university.repository.bookingDetails;
import com.university.repository.routeDetails;
import com.university.repository.scheduleDetails;
import com.university.service.scheduleService;


@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
@ContextConfiguration(locations =  {"classpath*:/spring/test-context.xml"})
public class ScheduleServiceTest {
	

	
	@Mock
    private bookingDetails bookingRepo;
	
	@Mock
	private scheduleDetails scheduleRepo;
	
	@Mock
	private routeDetails routeRepo;

    @InjectMocks
    private scheduleService scheduleService;
    
    private Schedule schedule;
    
    private Bookings booking;
    
    private Route route;
    
    
    @Test
    public void test_getAvailableShuttles() {
    	schedule=new Schedule(1234,"stopName",java.sql.Time.valueOf("18:00:00"));
    	when(scheduleRepo.findAll()).thenReturn(List.of(schedule));
    	List<Schedule> scheduleFetched=scheduleService.getAvailableShuttles();
    	assertThat(scheduleFetched).isNotNull();
    	assertThat(scheduleFetched.size()).isEqualTo(1);
    }
    
    @Test
    public void test_getRouteDetails() {
    	route=new Route(1234,"stopName1","stopName2","stopName3");
    	when(routeRepo.findAll()).thenReturn(List.of(route));
    	List<Route> routeFetched=scheduleService.getRouteDetails();
    	assertThat(routeFetched).isNotNull();
    	assertThat(routeFetched.size()).isEqualTo(1);
    }
    
    @Test
    public void test_getScheduleTimings() {
    	Schedule schedule1=new Schedule(1234,"stopName1",java.sql.Time.valueOf("18:00:00"));
    	Schedule schedule2=new Schedule(1234,"stopName1",java.sql.Time.valueOf("19:00:00"));
    	when(scheduleRepo.findAllBystopName("stopName1")).thenReturn(List.of(schedule1,schedule2));
    	List<java.sql.Time> timingsFetched=scheduleService.getScheduleTimings("stopName1");
    	assertThat(timingsFetched).isNotNull();
    	assertThat(timingsFetched.size()).isEqualTo(2);
    }
    
    @Test
    public void test_scheduleRide() {
    	booking=new Bookings(1234,"stopName1",java.sql.Time.valueOf("18:00:00"));
    	when(bookingRepo.save(booking)).thenReturn(booking);
    	Bookings bookingSaved=scheduleService.scheduleRide(booking);
    	assertThat(bookingSaved).isNotNull();
    	
    }
    
    @Test
    public void test_getBookingDetails() {
    	booking=new Bookings(1234,"stopName",java.sql.Time.valueOf("18:00:00"));
    	when(bookingRepo.findAllBystudentId(booking.getStudentId())).thenReturn(List.of(booking));
    	List<Bookings> bookingsFetched=scheduleService.getBookingDetails(booking.getStudentId());
    	assertThat(bookingsFetched).isNotNull();
    	assertThat(bookingsFetched.size()).isEqualTo(1);
    }

}
