package com.university.controller;

import java.util.List;
import java.util.Optional;

import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.university.model.Login;
import com.university.model.Student;
import com.university.service.LoginService;
import com.university.service.RegistrationService;

import ch.qos.logback.core.LogbackException;

@RestController
public class RegistrationController {
	
	@Autowired
	private RegistrationService regService;
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/register")
	@CrossOrigin()
	public ResponseEntity<Object> newRegistration(@RequestBody Login stLogin){
		try {
		if(!regService.fetchId(stLogin.getId()).isEmpty()) {
		if(loginService.checkNotAUser(stLogin.getId()).isEmpty()) {
			if(loginService.saveNewLogin(stLogin)!=null) {
				return ResponseEntity.status(201).body(stLogin);
			}
			else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to save user at this moment");
			}
		}
		else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("User already registered");
		}
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not a student of the University");
		}
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to save user at this moment");
		}
		
		
	}
	
	@PostMapping("/login")
	@CrossOrigin()
	public ResponseEntity<Object> login(@RequestBody Login login){
		Optional<Login> studentlogin;
		System.out.println(login.getId()+login.getPassword());
		if(!loginService.checkNotAUser(login.getId()).isEmpty()) {
			studentlogin=loginService.login(login.getId(),login.getPassword());
			if(!studentlogin.isEmpty()) {
				System.out.println("Inside ok");
				return ResponseEntity.status(HttpStatus.OK).body(studentlogin);
			}
			else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(studentlogin);
			}
		}
		else {
			if(!regService.fetchId(login.getId()).isEmpty()){
				return ResponseEntity.status(HttpStatus.OK).body(null);
			}
			else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not a student of the University");
			}
			
		}
		
		
	}
	
	
	@GetMapping("/users")
	@CrossOrigin()
	public List<Student> allUsers() throws Exception{
		List<Student> allStudents=regService.fetchAllStudents();
		return allStudents;
	}
	
	@GetMapping("/studentDetails")
	@CrossOrigin()
	public Optional<Student> getStudentDetails(@RequestParam(value="id") int id) throws Exception{
		return regService.fetchId(id);
	}

}
