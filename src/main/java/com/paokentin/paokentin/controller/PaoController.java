package com.paokentin.paokentin.controller;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.paokentin.paokentin.model.Pao;
import com.paokentin.paokentin.repositories.PaoRepositorio;


@RestController
public class PaoController {
	
	@CrossOrigin(origins = "*")
	@PostMapping("/pao")
	public void inserir(@RequestBody Pao pao) {
		try {
			PaoRepositorio.getInstance().inserir(pao);
		} catch (SQLException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao inserir o pão.");
		}
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/pao")
	public ResponseEntity<?> lerTodos() {
		try {
			return new ResponseEntity<>(PaoRepositorio.getInstance().lerTodos(), HttpStatus.OK);
		} catch (SQLException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao ler os pães.");
		}
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/pao/{codigo}")
	public ResponseEntity<?> ler(@PathVariable("codigo") int codigo) {
		try {
			return new ResponseEntity<>(PaoRepositorio.getInstance().ler(codigo), HttpStatus.OK);
		} catch (SQLException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao ler o pão.");
		}
	}
	
	@CrossOrigin(origins = "*")
	@PutMapping("/pao")
	public void atualizar(@RequestBody Pao pao) {
		try {
			PaoRepositorio.getInstance().atualizar(pao);
		} catch (SQLException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar o pão.");
		}
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping("/pao/{codigo}")
	public void deletar(@PathVariable("codigo") int codigo) {
		try {
			PaoRepositorio.getInstance().deletar(codigo);
		} catch (SQLException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar o pão.");
		}
	}

}
