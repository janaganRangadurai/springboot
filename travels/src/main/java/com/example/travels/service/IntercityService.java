package com.example.travels.service;

import java.util.List;
import java.util.Optional;

import com.example.travels.model.Intercity;

public interface IntercityService {
	
	List<Intercity> showAllTrip();
	
	Optional<Intercity> searchtrip(String id);
	
	Intercity addNewtrip(Intercity intercity);
	
	void deleteTrip(String id);

}
