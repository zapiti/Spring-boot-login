package com.una.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.una.model.Bolo;
import com.una.model.Cobertura;

public interface CoberturaRepository extends CrudRepository<Cobertura,Long>{
	
	 List<Cobertura> findByNomeLike(String nome);
	}