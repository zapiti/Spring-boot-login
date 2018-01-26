package com.una.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.una.model.Cobertura;
import com.una.model.Massa;

public interface MassaRepository extends CrudRepository<Massa,Long>{
	 List<Massa> findByCorLike(String cor);
}
