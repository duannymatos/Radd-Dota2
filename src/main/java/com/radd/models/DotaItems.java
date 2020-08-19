package com.radd.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class DotaItems implements Serializable
{
	static final long serialVersionUID = 1L;
	@Id
	@Column(name="item_id")
	private String itemId;
	@Column(name="item_name")
	private String itemName;
	private String img;
	private String notes;
	private String price;
	public String getItem_id() {
		return itemId;
	}
	public void setItem_id(String item_id) {
		this.itemId = item_id;
	}
	public String getItem_name() {
		return itemName;
	}
	public void setItem_name(String item_name) {
		this.itemName = item_name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	
}
