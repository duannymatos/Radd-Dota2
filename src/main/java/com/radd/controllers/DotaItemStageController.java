package com.radd.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.radd.models.DotaItemStage;
import com.radd.models.DotaItems;
import com.radd.services.DotaItemStageService;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/heroes/item")
public class DotaItemStageController {

	@Autowired
	DotaItemStageService service;
	
	@GetMapping
	public List<DotaItemStage> getAll(){
		return service.getAllItemStages();
	}
	
	@GetMapping(value = "/{id}" , produces="application/json")
		public List<DTOItemsStage> byHeroId(@PathVariable("id")String id) 
	{
		List<DotaItemStage> test = service.getItemsByHero(id);
		//Constantly adding to populate it so using linked list
		List<DTOItemsStage> retur = new LinkedList();

		DTOItemsStage stageItem= null;
		
		
		//System.out.println(test.get(0).getItemId().getImg());
		//System.out.println(test.get(0).getStage().getStage());
		
		//loop through and add each DTO object to reduce the ammount of outputted data
		if(test!=null)
		{
			for(int i=0; i<test.size();i++)
			{
				if(test.get(i).getStage().getStage()!=null && test.get(i).getItemId()!=null)
				{
					stageItem=new DTOItemsStage(test.get(i).getStage().getStage(),test.get(i).getItemId());
					retur.add(stageItem);
				}
				else
				{
					System.out.println("Error found");
				}
			}
		}
		return retur;
	}
	
	
	class DTOItemsStage
	{
		//private DotaItem itemId;
		private String stage;
		private DotaItems item;
		
		public DTOItemsStage(String s, DotaItems i)
		{
			this.stage=s;
			this.item=i;
		}
		public String getStage()
		{
			return stage;
		}
		public DotaItems getItem()
		{
			return item;
		}
	}
	
	class AddItemToHero
	{
		private String itemId;
		private String heroId;
		private String stage;
		
		public String getItemId() 
		{
			return itemId;
		}
		public void setItemId(String itemId) 
		{
			this.itemId = itemId;
		}
		public String getHeroId()
		{
			return heroId;
		}
		public void setHeroId(String heroId)
		{
			this.heroId = heroId;
		}
		public String getStage()
		{
			return stage;
		}
		public void setStage(String stage) 
		{
			this.stage = stage;
		}
	}
}

