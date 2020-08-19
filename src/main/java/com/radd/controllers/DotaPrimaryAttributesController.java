package com.radd.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.radd.models.DotaPrimaryAttributes;
import com.radd.services.DotaPrimaryAttributesService;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/attributes")
public class DotaPrimaryAttributesController {

		@Autowired
		DotaPrimaryAttributesService serv;
		
		@GetMapping
		public List<DotaPrimaryAttributes> getAll() {
			return serv.getAllAtributes();
		}
}
