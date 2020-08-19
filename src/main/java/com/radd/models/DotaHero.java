package com.radd.models;
//TEST for git
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Entity
@Component
public class DotaHero implements Serializable {
	static final long serialVersionUID = 1L;

	@Id
	@Column(name="hero_id")
	private String heroId;
	
	@Column(name="hero_name")
	private String heroName;
	
	private String img;

	@ManyToOne
	@JoinColumn(name = "primary_atri")
	private DotaPrimaryAttributes atri;
	
	private String attacktype;

	public String getHero_id() {
		return heroId;
	}

	public void setHero_id(String hero_id) {
		this.heroId = hero_id;
	}

	public String getHero_name() {
		return heroName;
	}

	public void setHero_name(String hero_name) {
		this.heroName = hero_name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public DotaPrimaryAttributes getPrimary_atri() {
		return atri;
	}

	public void setPrimary_atri(DotaPrimaryAttributes primary_atri) {
		this.atri = primary_atri;
	}

	public String getAttacktype() {
		return attacktype;
	}

	public void setAttacktype(String attacktype) {
		this.attacktype = attacktype;
	}

	
}
