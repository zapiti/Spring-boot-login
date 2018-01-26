package com.una.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Voto {

	@Id
	@GeneratedValue
	private Long id;
	@NotNull(message = "Campo obrigatório")
	private Integer qtd_voto;
	
	@NotNull(message = "Campo obrigatório")
	@ManyToOne
    @JoinColumn(name = "bolo_id", referencedColumnName = "id")
    private Bolo bolo;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQtd_voto() {
		return qtd_voto;
	}

	public void setQtd_voto(Integer qtd_voto) {
		this.qtd_voto = qtd_voto;
	}

	public Bolo getBolo() {
		return bolo;
	}

	public void setBolo(Bolo bolo) {
		this.bolo = bolo;
	}

	@Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	       Voto voto = (Voto) o;
	        return Objects.equals(id, voto.id);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(id);
	    }
	
}
