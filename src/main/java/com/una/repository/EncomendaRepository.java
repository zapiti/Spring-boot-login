package com.una.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.una.model.Encomenda;


public interface EncomendaRepository extends CrudRepository<Encomenda,Long>{
	 List<Encomenda> findByValorLike(Double valor);
}
