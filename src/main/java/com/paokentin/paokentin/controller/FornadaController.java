package com.paokentin.paokentin.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
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

import com.paokentin.paokentin.model.Fornada;
import com.paokentin.paokentin.model.Pao;
import com.paokentin.paokentin.repositories.FornadaRepositorio;
import com.paokentin.paokentin.repositories.PaoRepositorio;

@RestController
public class FornadaController {

	@CrossOrigin(origins = "*")
	@PostMapping("/fornada")
	public void inserir(@RequestBody Fornada fornada) {
		try {
			FornadaRepositorio.getInstance().inserir(fornada);
		} catch (SQLException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao inserir a fornada.");
		}
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/fornada/pao/{codigo}")
	public void inserirPorPao(@PathVariable("codigo") int codigo) {
		try {
			Pao p = PaoRepositorio.getInstance().ler(codigo);
			if (p == null) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao ler o código do pão.");
			}
			Fornada f = new Fornada();
			f.setPao(p);
			f.atualizaSaida();
			FornadaRepositorio.getInstance().inserir(f);
		} catch (SQLException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao inserir a fornada.");
		}
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/fornada")
	public ResponseEntity<?> lerTodos() {
		try {
			return new ResponseEntity<>(FornadaRepositorio.getInstance().lerTodos(), HttpStatus.OK);
		} catch (SQLException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao ler as fornadas.");
		}
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/fornada/{codigo}")
	public ResponseEntity<?> ler(@PathVariable("codigo") int codigo) {
		try {
			return new ResponseEntity<>(FornadaRepositorio.getInstance().ler(codigo), HttpStatus.OK);
		} catch (SQLException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao ler a fornada.");
		}
	}

	@CrossOrigin(origins = "*")
	@PutMapping("/fornada")
	public void atualizar(@RequestBody Fornada fornada) {
		try {
			FornadaRepositorio.getInstance().atualizar(fornada);
		} catch (SQLException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao inserir a fornada.");
		}
	}

	@CrossOrigin(origins = "*")
	@DeleteMapping("/fornada/{codigo}")
	public void deletar(@PathVariable("codigo") int codigo) {
		try {
			FornadaRepositorio.getInstance().deletar(codigo);
		} catch (SQLException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar a fornada.");
		}
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/fornada/pao/{codigo}")
	public ResponseEntity<?> lerUltimaPao(@PathVariable("codigo") int codigo) {
		try {
			Pao p = PaoRepositorio.getInstance().ler(codigo);
			Fornada fornada = null;
			for (Fornada f : FornadaRepositorio.getInstance().lerTodos()) {
				if (f.getPao().equals(p)) {
					fornada = f;
				}
			}
			return new ResponseEntity<>(fornada, HttpStatus.OK);
		} catch (SQLException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao ler as fornadas.");
		}
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/fornada/pao")
	public ResponseEntity<?> lerUltimas() {
		try {
			List<Fornada> ultimas = new ArrayList<>();
			List<Pao> paoList = PaoRepositorio.getInstance().lerTodos();
			for (int i = 0; i < paoList.size(); i++) {
				for (Fornada f : FornadaRepositorio.getInstance().lerTodos()) {
					if (f.getPao().equals(paoList.get(i))) {
						if (i >= ultimas.size()) {
							ultimas.add(i, f);
						} else {
							ultimas.set(i, f);
						}
					}
				}
			}
			return new ResponseEntity<>(ultimas, HttpStatus.OK);
		} catch (SQLException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao ler as fornadas.");
		}
	}

}
