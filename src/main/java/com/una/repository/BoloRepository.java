package com.una.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.una.model.Bolo;



public interface BoloRepository extends JpaRepository<Bolo, Long>{
	 Page<Bolo> findByNomeLike(String nome,Pageable pageable);
}
