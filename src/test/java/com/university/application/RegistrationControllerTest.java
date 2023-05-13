package com.university.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import com.university.controller.RegistrationController;
import com.university.model.Login;
import com.university.model.Student;
import com.university.service.LoginService;
import com.university.service.RegistrationService;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
@ContextConfiguration(locations =  {"classpath*:/spring/test-context.xml"})
public class RegistrationControllerTest {
	
	@Mock
	private LoginService loginService;
	
	@Mock
	private RegistrationService regService;
	
	@InjectMocks
	private RegistrationController controller;
	
	private Login login;
	private Student student;
	
	@BeforeEach
	public void setup() {
		login=new Login(1234,"password");
		student=new Student(1234,"fName","LName","emailId");
	}
	@SuppressWarnings("deprecation")
	@Test
	public void test_register_New_user() throws Exception {
		when(regService.fetchId(login.getId())).thenReturn(Optional.of(student));
		when(loginService.checkNotAUser(login.getId())).thenReturn(Optional.empty());
		when(loginService.saveNewLogin(login)).thenReturn(login);
			
		ResponseEntity<Object> newUser=controller.newRegistration(login);

		assertThat(newUser.getStatusCodeValue()).isEqualTo(201);
		assertThat(newUser.getBody()).isEqualTo(login);
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void test_dataBase_Issue() throws Exception {
		when(regService.fetchId(login.getId())).thenReturn(Optional.of(student));
		when(loginService.checkNotAUser(login.getId())).thenReturn(Optional.empty());
		when(loginService.saveNewLogin(login)).thenReturn(null);
			
		ResponseEntity<Object> newUser=controller.newRegistration(login);

		assertThat(newUser.getStatusCodeValue()).isEqualTo(500);
		
	}

	@SuppressWarnings("deprecation")
	@Test
	public void test_user_already_registered() throws Exception {
		when(regService.fetchId(login.getId())).thenReturn(Optional.of(student));
		when(loginService.checkNotAUser(login.getId())).thenReturn(Optional.of(login));
			
		ResponseEntity<Object> newUser=controller.newRegistration(login);

		assertThat(newUser.getStatusCodeValue()).isEqualTo(409);
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void test_not_a_student() throws Exception {
		when(regService.fetchId(login.getId())).thenReturn(Optional.empty());
			
		ResponseEntity<Object> newUser=controller.newRegistration(login);

		assertThat(newUser.getStatusCodeValue()).isEqualTo(401);
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void test_login_user() throws Exception {
		when(loginService.checkNotAUser(login.getId())).thenReturn(Optional.of(login));
		when(loginService.login(login.getId(), login.getPassword())).thenReturn(Optional.of(login));
			
		ResponseEntity<Object> userLogin=controller.login(login);

		assertThat(userLogin.getStatusCodeValue()).isEqualTo(200);
		assertThat(userLogin.getBody()).isEqualTo(Optional.of(login));
		
	}
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void test_Invalid_Credentials() throws Exception {
		when(loginService.checkNotAUser(login.getId())).thenReturn(Optional.of(login));
		when(loginService.login(login.getId(), login.getPassword())).thenReturn(Optional.empty());
			
		ResponseEntity<Object> userLogin=controller.login(login);

		assertThat(userLogin.getStatusCodeValue()).isEqualTo(401);
		assertThat(userLogin.getBody()).isEqualTo(Optional.empty());
		
	}
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void test_student_not_registered() throws Exception {
		when(loginService.checkNotAUser(login.getId())).thenReturn(Optional.empty());
		when(regService.fetchId(login.getId())).thenReturn(Optional.of(student));
		
			
		ResponseEntity<Object> userLogin=controller.login(login);

		assertThat(userLogin.getStatusCodeValue()).isEqualTo(200);
		assertThat(userLogin.getBody()).isEqualTo(null);
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void test_not_a_Student_during_login() throws Exception {
		when(loginService.checkNotAUser(login.getId())).thenReturn(Optional.empty());
		when(regService.fetchId(login.getId())).thenReturn(Optional.empty());
		
			
		ResponseEntity<Object> userLogin=controller.login(login);

		assertThat(userLogin.getStatusCodeValue()).isEqualTo(401);
		
	}
	
	@Test
	public void test_fetch_all_students() throws Exception {
		when(regService.fetchAllStudents()).thenReturn(List.of(student));
	
		List<Student> students=controller.allUsers();

		assertThat(students.size()).isEqualTo(1);
		
	}
	
	@Test
	public void test_fetch_student() throws Exception {
		when(regService.fetchId(student.getId())).thenReturn(Optional.of(student));
	
		Optional<Student> studentFetched=controller.getStudentDetails(student.getId());

		assertThat(studentFetched).isNotNull();
		assertThat(studentFetched).isEqualTo(Optional.of(student));
		
	}
	
}
