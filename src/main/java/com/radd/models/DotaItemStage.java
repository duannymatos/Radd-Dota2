package com.radd.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Entity
@Component
public class DotaItemStage implements Serializable
{


	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="hero_id")
	private DotaHero heroId;
	
	
	
	@ManyToOne
	@JoinColumn(name="item_id")
	private DotaItems itemId;
	
	/*
	@OneToMany(mappedBy="stage")
	private List<ItemStage> stage;
	*/
	
	@ManyToOne
	@JoinColumn(name="stage")
	private DotaStage stage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DotaHero getHeroId() {
		return heroId;
	}

	public void setHeroId(DotaHero heroId) {
		this.heroId = heroId;
	}

	public DotaItems getItemId() {
		return itemId;
	}

	public void setItemId(DotaItems itemId) {
		this.itemId = itemId;
	}

	public DotaStage getStage() {
		return stage;
	}

	public void setStage(DotaStage stage) {
		this.stage = stage;
	}
	
	
	
}
