package com.una.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Encomenda {

	@Id
	@GeneratedValue
	private Long id;
	@NotNull(message = "Campo obrigatório")
	
	private Double  valor=0.0;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date entrega;
	
	@NotNull(message = "Campo obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bolo_id", referencedColumnName = "id")
    private Bolo bolo;
	
	
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getEntrega() {
		return entrega;
	}

	public void setEntrega(Date entrega) {
		this.entrega = entrega;
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
	       Encomenda encomenda = (Encomenda) o;
	        return Objects.equals(id, encomenda.id);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(id);
	    }
	
}
