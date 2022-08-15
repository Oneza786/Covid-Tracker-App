package com.java.covidtracker.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.java.covidtracker.models.LocationStats;
import com.java.covidtracker.services.CovidDataServices;

@Controller  //becz it is web app so not using restcontroller(it is for WS)
public class HomeController {

	 @Autowired
	    CovidDataServices covidDataService;
	 
	@GetMapping("/")
	public String Home(Model model) {
		  List<LocationStats> allStats = covidDataService.getAllStats();
		  int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		  int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		  LocalDateTime dateTime=LocalDateTime.now();
	        model.addAttribute("locationStats", allStats);
	        model.addAttribute("totalReportedCases", totalReportedCases);
	        model.addAttribute("totalNewCases", totalNewCases);
	        model.addAttribute("dateTime", dateTime);
		return "home";
	}
	
	
}

//http://localhost:8080/
