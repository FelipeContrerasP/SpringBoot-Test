package com.nisum.springboot.test.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.springboot.test.app.models.entity.Usuario;
import com.nisum.springboot.test.app.models.services.IUsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;

	@PostMapping("/usuarios")
	public ResponseEntity<?> crearU(@Valid @RequestBody Usuario usuario, BindingResult result) {
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "Error en el campo '" + err.getField() + "' :" + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (usuarioService.findByEmail(usuario.getEmail()) != null) {
			response.put("mensaje", "El correo ya está registrado");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			usuarioService.save(usuario);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la inserción a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario ha sido creado con éxito!");
		response.put("usuario", usuario);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
