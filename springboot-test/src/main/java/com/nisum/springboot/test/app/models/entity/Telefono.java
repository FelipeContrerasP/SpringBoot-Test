package com.nisum.springboot.test.app.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "telefonos")
public class Telefono implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer number;
	
	private Integer citycode;
	
	private Integer countrycode;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	public Telefono() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getCitycode() {
		return citycode;
	}

	public void setCitycode(Integer citycode) {
		this.citycode = citycode;
	}

	public Integer getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(Integer countrycode) {
		this.countrycode = countrycode;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	} 
	
	
	private static final long serialVersionUID = 1L;
}
