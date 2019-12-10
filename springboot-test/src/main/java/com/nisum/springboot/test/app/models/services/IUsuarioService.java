package com.nisum.springboot.test.app.models.services;

import java.util.List;

import com.nisum.springboot.test.app.models.entity.Usuario;

public interface IUsuarioService {
	
	public List<Usuario> findAll();
	
	public void save(Usuario usuario);
	
	public Usuario findOne(Long id);
	
	public void delete(Long id);
	
	public Usuario findByEmail(String email);

}
