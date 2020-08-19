package com.radd.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.radd.models.DotaStage;
import com.radd.services.DotaStageService;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
@RequestMapping("/stage")
public class DotaStageController 
{
	
	@Autowired
	DotaStageService serv;
	
	@GetMapping
	public List<DotaStage> main()
	{
		return serv.getAllStages();
	}
}
