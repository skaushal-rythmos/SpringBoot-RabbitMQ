package com.example.demo.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Places;

@Service
public class PlacesService {
	
	private List<Places> place = new ArrayList<>(Arrays.asList(
			new Places("001","delhi","India")));
	
	public List<Places> getAllplaces(){
		
		return place;
	}

	public void addplace(Places item) {
		
		
		place.add(item);
		
		
	}

}
