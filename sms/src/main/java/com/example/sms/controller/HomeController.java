package com.example.sms.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sms.model.Student;
import com.example.sms.service.StudentService;



@Controller
public class HomeController {
	
	@Autowired
	private StudentService stuService;
	
	@GetMapping("/")
	public String homepage()
	{
		return "index";
	}
	
	@GetMapping("/view")
	public String viewpage(Model model) {

		model.addAttribute("allstudents", stuService.showAllStudents());
		
		return "view-student";
	}
	
	@GetMapping("/add")
	public String addpage(Model model)
	{
		model.addAttribute("obj", new Student());
		return "add-student";
	}
	
	@PostMapping("/add")
	public String addStudentData(@ModelAttribute("obj") Student stu)
	{
		
	Optional<Student> opt= stuService.searchStudent(stu.getRollno());
	
	if(opt.isEmpty())
	{
		stuService.addNewStudent(stu);
		return "redirect:/view";
	}
	else
	{
		return "redirect:/add?failed";
	}
	}
	
	@GetMapping("/edit/{id}")
	public String editpage(@PathVariable("id") int rollno, Model model)
	{
		
		Optional<Student> fechData= stuService.searchStudent(rollno);
		model.addAttribute("obj",fechData);
		return "edit-student";
	}
	
	@PostMapping("/edit")
	public String editData(@ModelAttribute("obj") Student stu)
	{
		stuService.addNewStudent(stu);
		return "redirect:/view";
	}
	
	@GetMapping("/delete/{id}")
	public String deletepage(@PathVariable("id") int rollno, Model model)
	{
		stuService.deleteStudent(rollno);
		return "redirect:/view";
		
	}
	
	@GetMapping("/search-male")
	public String getMaleStudent(Model model) 
	{
		model.addAttribute(("allstudents"),stuService.serachGenderWise("male"));
		return "view-student";
	}
	
	@GetMapping("/search-female")
	public String getFemaleStudent(Model model)
	{
		model.addAttribute(("allstudents"),stuService.serachGenderWise("female"));
		return "view-student";
	}
	
	@PostMapping("/search-std")
	public String getStudentsStdWise(@RequestParam("std")int std, Model model)
	{
		model.addAttribute("allstudents",stuService.searchStdWise(std));
		return "view-student";
	}
	
	@GetMapping("/search-query")
	public String getStudentQueryWise(@RequestParam("name") String name, @RequestParam("gender") String gender, Model model)
	{
		model.addAttribute("allstudents",stuService.searchStudentNameAndGenderWise(name, gender));
		return "view-student";
	}

}
