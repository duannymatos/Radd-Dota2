package com.radd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.radd.models.DotaItems;

@Repository
public interface DotaItemsRepository extends JpaRepository<DotaItems, String> {
	public DotaItems findByItemName(String name);
	public DotaItems findByItemId(String id);
}
