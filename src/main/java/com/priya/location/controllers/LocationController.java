package com.priya.location.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.priya.location.entities.Location;
import com.priya.location.service.LocationService;

@Controller
public class LocationController {
	@Autowired
	LocationService service;
	
//	this method will retrieve the initial create location page
	@RequestMapping("/showCreate")
	public String showCreate(){
		return "createLocation";
	}
	
//	this mehtod will execute after user add all values in create location page and hit save button
	@RequestMapping("/saveLoc")
	public String saveLocation(@ModelAttribute("location")Location location,ModelMap modelMap) {
		Location savedLocation = service.saveLocation(location);
		String msg="Location saved with the id: "+savedLocation.getId();
		modelMap.addAttribute("msg", msg);
		return "createLocation";
	}
	
//	this method will run when user click View All button on the create page and retrieve page with list of locations 
	@RequestMapping("/displayLocations")
	public String displayLocations(ModelMap modelMap) {
		List<Location> locations = service.getAllLocations();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}
	
//	this method will run, when user click delete link and it will delete particular location from database and retrieve page with other lists of location details.
	@RequestMapping("/deleteLocation")
	public String deleteLocation(@RequestParam("id")int id,ModelMap modelMap) {
		Location location=service.getLocationById(id);
		service.deleteLocation(location);
		List<Location> locations = service.getAllLocations();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}
	
//	when user click edit link in the display location page, this method will run and retrieve page for update particular location details
	@RequestMapping("/showUpdate")
	public String showUpdate(@RequestParam("id")int id,ModelMap modelMap){
		Location location=service.getLocationById(id);
		modelMap.addAttribute("location", location);
		return "updateLocation";
	}
	
//	this method used to update location detail and after update, will retrieve list of location details page.
	@RequestMapping("/updateLoc")
	public String updateLocation(@ModelAttribute("location")Location location,ModelMap modelMap) {
		service.updateLocation(location);
		List<Location> locations = service.getAllLocations();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}

}
