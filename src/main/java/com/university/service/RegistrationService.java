package com.university.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.model.Student;
import com.university.repository.studentDetails;

@Service
public class RegistrationService{
	
	@Autowired
	private studentDetails studentRepo;
	
	
	public Student saveNewUser(Student student) {
		return studentRepo.save(student);
	}
	
	public Optional<Student> fetchId(int studentId) {
		return studentRepo.findById(studentId);
	}
	
	public List<Student> fetchAllStudents(){
		return studentRepo.findAll();
	}

	

}
