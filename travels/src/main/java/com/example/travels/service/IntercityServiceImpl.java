package com.example.travels.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.travels.model.Intercity;
import com.example.travels.repository.IntercityRepository;

@Service
public class IntercityServiceImpl implements IntercityService {
	
	@Autowired
	private IntercityRepository interRepo;

	@Override
	public List<Intercity> showAllTrip() {
		
		return interRepo.findAll();
	}

	@Override
	public Optional<Intercity> searchtrip(String id) {
		
		return interRepo.findById(id);
	}

	@Override
	public Intercity addNewtrip(Intercity intercity) {
		
		return interRepo.save(intercity);
	}

	@Override
	public void deleteTrip(String id) {
		
		interRepo.deleteById(id);
		
	}

	

}
