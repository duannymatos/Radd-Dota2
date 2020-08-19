package com.radd.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radd.models.DotaItems;
import com.radd.repositories.DotaItemsRepository;

@Service
public class DotaItemsService {

	@Autowired
	DotaItemsRepository repo;
	
	public List<DotaItems> getItems(){
		return repo.findAll();
	}
	
	public DotaItems getItemByName(String name) {
		return repo.findByItemName(name);
	}
	
	public DotaItems getItemById(String id) {
		return repo.findByItemId(id);
	}
	
	public DotaItems createItem(DotaItems item) {
		return repo.save(item);
	}
	
	public DotaItems updatItem(DotaItems item) {
		return repo.save(item);
	}
	
	public void deleteItem(DotaItems item) {
		repo.delete(item);
	}
	
}
