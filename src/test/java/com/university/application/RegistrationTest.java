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
import org.springframework.test.context.ContextConfiguration;

import com.university.model.Student;
import com.university.repository.studentDetails;
import com.university.service.RegistrationService;


@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
@ContextConfiguration(locations =  {"classpath*:/spring/test-context.xml"})
public class RegistrationTest {
	
	@Mock
    private studentDetails studentRepo;
	

    @InjectMocks
    private RegistrationService regService;

    private Student student;
    
    @BeforeEach
    public void setup(){
        student = new Student(1234,"fName","Lname","email@gmail.com");
    }
    
    @Test
    public void testFetchId_result_One_object() {
    	when(studentRepo.findById(student.getId())).thenReturn(Optional.of(student));
    	Optional<Student> studentFetched=regService.fetchId(student.getId());
    	 assertThat(studentFetched).isNotEmpty();
    }
    
    @Test
    public void test_fetchAllUsers() {
    	Student student2 = new Student(1235,"fName","Lname","email@gmail.com");
    	when(studentRepo.findAll()).thenReturn(List.of(student,student2));
    	List<Student> studentsFetched=regService.fetchAllStudents();
    	 assertThat(studentsFetched).isNotNull();
    	 assertThat(studentsFetched.size()).isEqualTo(2);
    }
    
    @Test
    public void test_saveNewUser() {
    	when(studentRepo.save(student)).thenReturn(student);
    	Student studentSaved=regService.saveNewUser(student);
    	assertThat(studentSaved).isNotNull();
    	
    }
}
