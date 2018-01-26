package com.una.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.una.model.Sabor;

public interface SaborRepository extends CrudRepository<Sabor,Long>{
	 List<Sabor> findByNomeLike(String nome);
}
