package com.una.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Massa {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "Campo obrigatório")
    private String cor;
    
    
    



    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Massa massa = (Massa) o;
        return Objects.equals(id, massa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
