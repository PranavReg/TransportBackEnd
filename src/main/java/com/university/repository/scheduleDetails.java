package com.university.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.university.model.Schedule;

@Repository
public interface scheduleDetails extends JpaRepository<Schedule, String>{
	
	List<Schedule> findAllBystopName(String stopName);
}
