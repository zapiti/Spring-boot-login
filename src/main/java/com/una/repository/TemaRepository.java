package com.una.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.una.model.Tema;

public interface TemaRepository extends CrudRepository<Tema,Long>{
	 List<Tema> findByDescricaoLike(String descricao);
}
