package com.radd.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

@Entity
@Component
public class DotaStage implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String stage;

	
	
	public String getStage() 
	{
		return stage;
	}

	public void setStage(String stage) 
	{
		this.stage = stage;
	}
	
	
	/*
	@Id
	@ManyToOne
	private DotaStage stage;
	*/
	
}
