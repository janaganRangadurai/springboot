package com.example.sms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sms.model.Student;
import com.example.sms.service.StudentService;

@RestController
public class StudentRestController {
	
	@Autowired
	private StudentService studentService;
	
	
	@GetMapping("/api/students")
	public List<Student> getAllStudents()
	{
		return studentService.showAllStudents();
	}
	
	@GetMapping("/api/students/{std}")
	public List<Student> getStudentsStdWise(@PathVariable("std") int std)
	{
		return studentService.searchStdWise(std);
	}
	// add
	@GetMapping("/api/student/{id}")
	public Optional<Student> getStudentIdWise(@PathVariable("id")int rollno)
	{
		return studentService.searchStudent(rollno);
	}
	
	@PostMapping("/api/student")
	public Student addStudentData(@RequestBody Student stu)
	{
		Optional<Student> opt= studentService.searchStudent(stu.getRollno());
		if(opt.isEmpty())
			return studentService.addNewStudent(stu);
		else
			return new Student();
	}
	//edit
	@PutMapping("/api/student")
	public Student updateStudentData(@RequestBody Student stu)
	{
		Optional<Student> Opt= studentService.searchStudent(stu.getRollno());
				if(Opt.isPresent())
					return studentService.addNewStudent(stu);
				else
					return new Student();
	}
	
	@DeleteMapping("/api/student/{id}")
	public Optional<Student> deleteStudentData(@PathVariable("id")int rollno)
	{
		Optional<Student> opT= studentService.searchStudent(rollno);
		if(opT.isPresent())
		{
			 studentService.deleteStudent(rollno);
				return opT;
		}
		else
		{
			return opT;
		}
	
	
	
	

}}
