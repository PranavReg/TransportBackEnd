package com.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.university.model.Route;

@Repository
public interface routeDetails extends JpaRepository<Route, Integer>{

}
