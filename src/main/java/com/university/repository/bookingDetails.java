package com.university.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.university.model.Bookings;

@Repository
public interface bookingDetails extends JpaRepository<Bookings, Integer>{
	List<Bookings> findAllBystudentId(int studentId);
}
