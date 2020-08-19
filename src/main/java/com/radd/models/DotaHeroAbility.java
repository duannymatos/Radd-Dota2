package com.radd.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import com.radd.compostitekey.DotaHeroAbilityCompositeKey;

@Entity
@IdClass(DotaHeroAbilityCompositeKey.class)
@Component
public class DotaHeroAbility implements Serializable
{
	public String getAbility_name() {
		return ability_name;
	}

	public void setAbility_name(String ability_name) {
		this.ability_name = ability_name;
	}

	public DotaHero getHero_id() {
		return heroId;
	}

	public void setHero_id(DotaHero hero_id) {
		this.heroId = hero_id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	private static final long serialVersionUID = 1L;
	
	@Id
	private String ability_name;
	
	
	@ManyToOne
	@JoinColumn(name="hero_id")
	@Id
	DotaHero heroId;
	
	private String img;
}
