package com.nisum.springboot.test.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nisum.springboot.test.app.models.entity.Usuario;

@Repository
public interface IUsuarioDao extends CrudRepository<Usuario,Long> {
	
	@Query("select u from Usuario u where u.email=?1")
	Usuario findByEmail(String email);
}
