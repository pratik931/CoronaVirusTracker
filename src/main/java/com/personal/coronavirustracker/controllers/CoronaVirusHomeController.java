package com.personal.coronavirustracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.personal.coronavirustracker.models.LocationStats;
import com.personal.coronavirustracker.services.CoronaVirusDataService;

@Controller
public class CoronaVirusHomeController {
	@Autowired
	CoronaVirusDataService coronaVirusDataService;
	
	
	@RequestMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats = coronaVirusDataService.getAllStats();
		int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int diffFromPrevDay = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalCases", totalCases);
		model.addAttribute("diffFromPrevDay", diffFromPrevDay);
		return "home";
	}
}
