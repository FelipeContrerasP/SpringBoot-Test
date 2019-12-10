package com.nisum.springboot.test.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	
	@Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$", message = "El formato del correo debe ser válido ej: aaaaaaa@dominio.cl")
	private String email;
	
	@Pattern(regexp ="^[A-Z]{1}[a-z]{2,10}[0-9]{2}+$" ,message="El formato debe ser correcto(Una Mayuscula, letras minusculas y 2 numeros)")
	private String password;

	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Telefono> phones;

	@CreationTimestamp
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created;

	@UpdateTimestamp
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modified;

	@UpdateTimestamp
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date last_login;

	private String token;

	@PrePersist
	public void prePersist() {
		token = UUID.randomUUID().toString();
	}

	private boolean isActive = true;

	public Usuario() {
		phones = new ArrayList<Telefono>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Telefono> getPhones() {
		return phones;
	}

	public void setPhones(List<Telefono> phones) {
		this.phones = phones;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	private static final long serialVersionUID = 1L;
}
