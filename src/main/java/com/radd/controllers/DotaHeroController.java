package com.radd.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.radd.config.JwtTokenUtil;
import com.radd.models.CounterDTO;
import com.radd.models.DotaCounters;
import com.radd.models.DotaHero;
import com.radd.services.DotaCounterService;
import com.radd.services.DotaHeroService;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/heroes")
public class DotaHeroController 
{
	@Autowired
	DotaHeroService serv;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@GetMapping
	public List<DotaHero> main()
	{
		return serv.getAllHeros();
	}
	
	//NEED TO ADD ADMIN SECURITY-----------------------------------------------
	@PutMapping
	public ResponseEntity<String> updateHero(@RequestBody DotaHero hero) 
	{
		DotaHero updatedHero = serv.updateHero(hero);
		return new ResponseEntity<>("UpdatedHero: " + updatedHero.getHero_id(), HttpStatus.ACCEPTED);
	}


	
	
	
	@GetMapping(value ="/{id}", produces="application/json")
	public DotaHero byId(@PathVariable("id")String id) {
		return serv.getHeroById(id);
	}
	
	@GetMapping(value ="/atri/{value}", produces="application/json")
	public List<DotaHero> byAttribute(@PathVariable("value")String value) {
		return serv.getByAtri(value);
	}
	
	@Autowired
	DotaCounterService counterServ;
	@GetMapping (value ="/counter/{value}", produces="application/json")
	public Counterzz displayCounters(@PathVariable("value")String value)
	{
		DotaCounters counter = counterServ.getCounter(value);
		return new Counterzz(counter);
	}
	
	
	@GetMapping(value ="names/{name}", produces="application/json")
	public DotaHero byName(@PathVariable("name")String name) {
		return serv.getHeroByName(name);
	}
	
	
	@PutMapping("/counter")
	public ResponseEntity<?> updateCounter(@RequestBody CounterDTO dto, @RequestHeader(value="authorization",required = false) String token)
	{
		String role = new String("0");
		if(token!=null)
		{
			role= jwtTokenUtil.getRole(token);
		}
		
		
		DotaCounters counter = new DotaCounters();
		counter.setHeroId(dto.getRefHero());
		DotaHero best1=serv.getHeroById(dto.getBest1());
		DotaHero best2=serv.getHeroById(dto.getBest2());
		DotaHero best3=serv.getHeroById(dto.getBest3());
		DotaHero worst1=serv.getHeroById(dto.getWorst1());
		DotaHero worst2=serv.getHeroById(dto.getWorst2());
		DotaHero worst3=serv.getHeroById(dto.getWorst3());
		counter.setBest1(best1);
		counter.setBest2(best2);
		counter.setBest3(best3);
		counter.setWorst1(worst1);
		counter.setWorst2(worst2);
		counter.setWorst3(worst3);
		
		//check if they are an admin
		if(Integer.parseInt(role)>=1)
		{
			return new ResponseEntity<DotaCounters>(counterServ.updateCounter(counter),HttpStatus.CREATED);
		}
		
		return new ResponseEntity<String>("Must be an admin to modify heroes",HttpStatus.BAD_REQUEST);
		
		
	}

	class Counterzz
	{
		private DotaHero best1;
		private DotaHero best2;
		private DotaHero best3;
		private DotaHero worst1;
		private DotaHero worst2;
		private DotaHero worst3;
		
		Counterzz(DotaCounters counter)
		{
			best1=counter.getBest1();
			best2=counter.getBest2();
			best3=counter.getBest3();
			worst1=counter.getWorst1();
			worst2=counter.getWorst2();
			worst3=counter.getWorst3();
		}

		public DotaHero getBest1() {
			return best1;
		}

		public void setBest1(DotaHero best1) {
			this.best1 = best1;
		}

		public DotaHero getBest2() {
			return best2;
		}

		public void setBest2(DotaHero best2) {
			this.best2 = best2;
		}

		public DotaHero getBest3() {
			return best3;
		}

		public void setBest3(DotaHero best3) {
			this.best3 = best3;
		}

		public DotaHero getWorst1() {
			return worst1;
		}

		public void setWorst1(DotaHero worst1) {
			this.worst1 = worst1;
		}

		public DotaHero getWorst2() {
			return worst2;
		}

		public void setWorst2(DotaHero worst2) {
			this.worst2 = worst2;
		}

		public DotaHero getWorst3() {
			return worst3;
		}

		public void setWorst3(DotaHero worst3) {
			this.worst3 = worst3;
		}
	}
}
