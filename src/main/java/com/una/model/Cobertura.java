package com.una.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Cobertura {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "Campo obrigatório")
    private String nome;
    
    @NotBlank(message = "Campo obrigatório")
    private String tipo;

 

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cobertura cobertura = (Cobertura) o;
        return Objects.equals(id, cobertura.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
