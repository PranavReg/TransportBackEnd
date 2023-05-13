package com.university.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.model.Login;
import com.university.repository.loginDetails;

@Service
public class LoginService {
	
	@Autowired
	private loginDetails loginRepo;
	
	public Optional<Login> checkNotAUser(int id) {
		return loginRepo.findById(id);
	}
	
public Optional<Login> login(int id,String password) {
		
		return loginRepo.findByIdAndPassword(id, password);

	}

public Login saveNewLogin(Login login) {
	return loginRepo.save(login);
}

}
