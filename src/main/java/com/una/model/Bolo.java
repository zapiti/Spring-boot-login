package com.una.model;

import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Bolo {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String nome;
    
    @NotNull(message = "Campo obrigatório")
    private Double peso ;
    
    @NotNull(message = "Campo obrigatório")
    private Double valor ;

 
    @Formula("(SELECT AVG(v.qtd_voto + 0.0)  FROM voto v where v.bolo_id=id) ")
    private  Double mediaAvaliacao;

    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cobertura_id", referencedColumnName = "id")
    private Cobertura cobertura;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "massa_id", referencedColumnName = "id")
    private Massa massa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tema_id", referencedColumnName = "id")
    private Tema tema;
    

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="bolo_sabor",
            joinColumns=@JoinColumn(name="bolo_id"),
            inverseJoinColumns=@JoinColumn(name="sabor_id")
    )
    private Set<Sabor> sabores;
    
    

 public String getSabor() {
	 return this.sabores
			 .stream()
			 .map(Sabor::getNome)
			 .collect(Collectors.joining(", "));
 }

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}



	public Double getMediaAvaliacao() {
		return mediaAvaliacao;
	}

	public void setMediaAvaliacao(Double mediaAvaliacao) {
		this.mediaAvaliacao = mediaAvaliacao;
	}

	public Cobertura getCobertura() {
		return cobertura;
	}

	public void setCobertura(Cobertura cobertura) {
		this.cobertura = cobertura;
	}

	public Massa getMassa() {
		return massa;
	}

	public void setMassa(Massa massa) {
		this.massa = massa;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Set<Sabor> getSabores() {
		return sabores;
	}

	public void setSabores(Set<Sabor> sabores) {
		this.sabores = sabores;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bolo bolo = (Bolo) o;
        return Objects.equals(id, bolo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
