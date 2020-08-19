package com.radd.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="DotaPrimaryAtributes")
public class DotaPrimaryAttributes implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="primary_atri")
	private String primaryAtri;

	public String getPrimary_atri() {
		return primaryAtri;
	}

	public void setPrimary_atri(String primary_atri) {
		this.primaryAtri = primary_atri;
	}
	

}
