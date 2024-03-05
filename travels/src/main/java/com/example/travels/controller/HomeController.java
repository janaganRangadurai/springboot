package com.example.travels.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.travels.model.Intercity;
import com.example.travels.service.IntercityService;

@Controller
public class HomeController {
	
	@Autowired
	private IntercityService intercityService;
	
	@GetMapping("/")
	public String indexPage() {
		
		return "Intercity-index";
	}
	
	@GetMapping("/trip/view")
	public String veiwPage(Model model) {
		
		model.addAttribute("allTrips", intercityService.showAllTrip());
		return "intercity-view";
	}
	
	@GetMapping("/trip/add")
	public String addpage(Model model) {
		model.addAttribute("obj", new Intercity());
		
		return "intercity-add";
	}
	
	@PostMapping("/trip/add")
	public String addTrip(@ModelAttribute("obj") Intercity intercity) {
		
		Optional<Intercity> city= intercityService.searchtrip(intercity.getTripId());
	
		if(city.isEmpty()) {
			
			intercityService.addNewtrip(intercity);
			
			return "redirect:/trip/view";
		}
		else {
			
			return "redirect:/trip/add?failed";
		}
		
	}
	
	@GetMapping("/trip/edit/{id}")
	public String editpage(@PathVariable("id") String tripId, Model model) {
		
		model.addAttribute("obj",intercityService.searchtrip(tripId));
		
		return "intercity-edit";
	}
	
	@PostMapping("/trip/edit")
	public String editTrip(@ModelAttribute("obj") Intercity intercity) {
		
		intercityService.addNewtrip(intercity);
		
		return "redirect:/trip/view";
	}
	
	@GetMapping("/trip/delete/{id}")
	public String deleteTrip(@PathVariable("id") String tripId)
	{
		intercityService.deleteTrip(tripId);
		return "redirect:/trip/view";
	}

}
