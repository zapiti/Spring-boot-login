package com.una.repository;

import org.springframework.data.repository.CrudRepository;

import com.una.model.Image;



public interface ImageRepository extends CrudRepository<Image, Long> {
	
	public Image findByName(String name);

}
