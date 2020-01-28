package com.cpd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dne") 
public class DneController {
	
	@GetMapping(value="")
	public String sesmec(){
		return "dne";
	}
	
}
