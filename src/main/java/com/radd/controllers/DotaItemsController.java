package com.radd.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.radd.models.DotaItems;
import com.radd.services.DotaItemsService;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/items")
public class DotaItemsController {

		@Autowired
		DotaItemsService service;
		
		@GetMapping
		public List<DotaItems> getAll(){
			return service.getItems();
		}
		
		@GetMapping(value ="/{id}", produces="application/json")
		public DotaItems byId(@PathVariable("id")String id) {
			return service.getItemById(id);
		}
		
		
		@GetMapping(value ="/name/{name}", produces="application/json")
		public DotaItems byName(@PathVariable("name")String name) {
			return service.getItemByName(name);
		}
		
		@PostMapping
		public DotaItems create(@RequestBody DotaItems item) {
			return service.createItem(item);
		}
		
		@PutMapping
		public DotaItems update(@RequestBody DotaItems item) {
			return service.updatItem(item);
		}
		
		@DeleteMapping
		public void delete(@RequestBody DotaItems item) {
			service.deleteItem(item);
		}
	
		
		
}
