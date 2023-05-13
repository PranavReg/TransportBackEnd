package com.university.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.university.model.Login;

@Repository
public interface loginDetails extends JpaRepository<Login, Integer>{
	Optional<Login> findByIdAndPassword(int id, String password);
}
