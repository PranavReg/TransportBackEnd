package com.university.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;

import com.university.model.Login;
import com.university.repository.loginDetails;
import com.university.service.LoginService;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
@ContextConfiguration(locations =  {"classpath*:/spring/test-context.xml"})
public class loginServiceTest {
	
	
	@Mock
    private loginDetails loginRepo;

    @InjectMocks
    private LoginService loginService;
    
    private Login login;
    
    @BeforeEach
    public void setup(){
        login = new Login(1234,"password");
    }
    
    @Test
    public void test_checkNotAUser() {
    	when(loginRepo.findById(login.getId())).thenReturn(Optional.of(login));
    	Optional<Login> loginFetched=loginService.checkNotAUser(login.getId());
    	assertThat(loginFetched).isNotEmpty();
    }
    
    @Test
    public void test_login() {
    	when(loginRepo.findByIdAndPassword(login.getId(),login.getPassword())).thenReturn(Optional.of(login));
    	Optional<Login> loginFetched=loginService.login(login.getId(),login.getPassword());
    	 assertThat(loginFetched).isNotEmpty();
    }
    
    @Test
    public void test_saveNewLogin() {
    	when(loginRepo.save(login)).thenReturn(login);
    	Login loginSaved=loginService.saveNewLogin(login);
    	assertThat(loginSaved).isNotNull();
    	
    }
    
}
