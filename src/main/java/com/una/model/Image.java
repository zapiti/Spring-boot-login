package com.una.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Image {
	@Id
	@GeneratedValue
	private Integer id;

	private String path;
	private String name;
	
	
	//@ManyToOne(fetch = FetchType.EAGER)
   // @JoinColumn(name = "bolo_id", referencedColumnName = "id")
   // private Bolo bolo;
	//



	public Image() {

	}

	public Image(String name, String path) {
		this.name = name;
		this.path = path;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
