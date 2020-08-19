package com.radd.compostitekey;

import java.io.Serializable;

import com.radd.models.DotaHero;

public class DotaHeroAbilityCompositeKey implements Serializable{
	private String ability_name;
	private DotaHero heroId;
	
	public DotaHeroAbilityCompositeKey() {
		
	}
	
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

	public DotaHeroAbilityCompositeKey(String ability, DotaHero id) {
		this.ability_name = ability;
		this.heroId =id;
	}
}
