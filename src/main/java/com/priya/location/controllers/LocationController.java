package com.priya.location.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.priya.location.entities.Location;
import com.priya.location.service.LocationService;

@Controller
public class LocationController {
	@Autowired
	LocationService service;
	
	@RequestMapping("/showCreate")
	public String showCreate(){
		return "createLocation";
	}
	
	@RequestMapping("/saveLoc")
	public String saveLocation(@ModelAttribute("location")Location location,ModelMap modelMap) {
		Location savedLocation = service.saveLocation(location);
		String msg="Location saved with the id: "+savedLocation.getId();
		modelMap.addAttribute("msg", msg);
		return "createLocation";
	}

}
