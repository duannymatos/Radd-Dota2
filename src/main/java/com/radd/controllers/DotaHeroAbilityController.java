package com.radd.controllers;

import java.util.LinkedList;
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

import com.radd.models.DotaHeroAbility;
import com.radd.services.DotaHeroAbilityService;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/heroes/abilities")
public class DotaHeroAbilityController {

	@Autowired
	DotaHeroAbilityService serv;
	
	@GetMapping
	public List<DotaHeroAbility> test() {
		return serv.getAllAbilities();
	}
	
	@GetMapping(value ="/{id}", produces="application/json")
	public List byId(@PathVariable("id")String id) {
		List<DotaHeroAbility> placeHolder =serv.getAbilityByHeroId(id);
		List<HeroAbilities> abilities= new LinkedList();
		placeHolder.get(0).getAbility_name();
		placeHolder.get(0).getImg();
		//loop through and add
		HeroAbilities ability;
		for(int i=0; i<placeHolder.size();i++)
		{
			ability = new HeroAbilities(placeHolder.get(i).getAbility_name(),
			placeHolder.get(i).getImg());
			abilities.add(ability);
		}
		
		return abilities;
	}
	
	/*
	@GetMapping(value ="/name/{name}", produces="application/json")
	public List<DotaHeroAbility> byName(@PathVariable("name")String name) {
		return serv.getAbilityByHeroName(name);
	}
	
	@PutMapping(value="/update")
		public DotaHeroAbility update(@RequestBody DotaHeroAbility ability){
			return serv.updateAbility(ability);
		}
	@PostMapping(value = "/create")
	public DotaHeroAbility create(@RequestBody DotaHeroAbility ability) {
		return serv.createAbility(ability);
	}
	*/
	@DeleteMapping(value = "/delete")
	public void delete(@RequestBody DotaHeroAbility ability) 
	{
		serv.deleteAbility(ability);
	}
	
	
	class HeroAbilities
	{
		private String ability_name;
		private String img;
		
		
		public HeroAbilities(String ability_name, String img) {
			super();
			this.ability_name = ability_name;
			this.img = img;
		}
		public String getAbility_name() {
			return ability_name;
		}
		public void setAbility_name(String ability_name) {
			this.ability_name = ability_name;
		}
		public String getImg() {
			return img;
		}
		public void setImg(String img) {
			this.img = img;
		}
		
		
	}
}
