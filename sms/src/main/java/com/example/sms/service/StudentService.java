package com.example.sms.service;

import java.util.List;
import java.util.Optional;

import com.example.sms.model.Student;

public interface StudentService {
	
	List<Student> showAllStudents();
	
	Optional<Student> searchStudent(int rollno);
	
	Student addNewStudent(Student student);
	
	void deleteStudent(int rollno);
	
	List<Student> serachGenderWise(String gender);
	
	List<Student> searchStdWise(int std);
	
	List<Student> searchStudentNameAndGenderWise(String name, String gender);


}
