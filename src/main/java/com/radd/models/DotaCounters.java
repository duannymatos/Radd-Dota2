package com.radd.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;



@Entity

@Component
public class DotaCounters implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String heroId;
	
	
	@ManyToOne
	@JoinColumn(name="worst1")
	private DotaHero worst1;
	
	@ManyToOne
	@JoinColumn(name="worst2")
	private DotaHero worst2;

	@ManyToOne
	@JoinColumn(name="worst3")
	private DotaHero worst3;
	
	@ManyToOne
	@JoinColumn(name="best3")
	private DotaHero best3;
	
	@ManyToOne
	@JoinColumn(name="best2")
	private DotaHero best2;
	
	@ManyToOne
	@JoinColumn(name="best1")
	private DotaHero best1;

	public String getHeroId() {
		return heroId;
	}

	public void setHeroId(String dota_hero) {
		this.heroId = dota_hero;
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

	public DotaHero getBest3() {
		return best3;
	}

	public void setBest3(DotaHero best3) {
		this.best3 = best3;
	}

	public DotaHero getBest2() {
		return best2;
	}

	public void setBest2(DotaHero best2) {
		this.best2 = best2;
	}

	public DotaHero getBest1() {
		return best1;
	}

	public void setBest1(DotaHero best1) {
		this.best1 = best1;
	}
	
	
	
}
