package com.exercicio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.exercicio.entity.Cachorro;
import com.exercicio.repository.CachorroRepository;

@RestController
@RequestMapping
public class CachorroController {
	
	@Autowired
	CachorroRepository doguinhoRepo;
	
	@GetMapping("/cachorros")
	public ResponseEntity<List<Cachorro>> getCachorros() {
		List<Cachorro> cachorros = (List<Cachorro>) doguinhoRepo.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(cachorros);
	}
	
	@GetMapping("/cachorros/{idcachorro}")
	public ResponseEntity<Cachorro> getCachorrosById(@PathVariable("idcachorro") Long idCachorro) {
		Optional<Cachorro> cachorro = doguinhoRepo.findById(idCachorro);
		if (cachorro.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse doguinho não existe em nosso banco de dados!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(cachorro.get());
	}
	
	@PostMapping("/cachorros")
	public ResponseEntity<Cachorro> SaveCachorro(@RequestBody Cachorro cachorro) {
		Cachorro ct = doguinhoRepo.save(cachorro);
		return ResponseEntity.status(HttpStatus.CREATED).body(ct);
	}
	
	@PutMapping("/cachorros/{idcachorro}")
	public ResponseEntity<Cachorro> alterarContato(@PathVariable("idcachorro") Long idCachorro, @RequestBody Cachorro cachorro) {
		Optional<Cachorro> cao = doguinhoRepo.findById(idCachorro);
		if (cao.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não é possível alterar um doguinho inexistente no banco de dados!");
		}
		cachorro.setId(idCachorro);
		return ResponseEntity.ok(doguinhoRepo.save(cachorro));
	}
	
	@DeleteMapping("/cachorros/{idcachorro}")
	public ResponseEntity<Void> deleteContato(@PathVariable("idcachorro") Long idCachorro) {
		Optional<Cachorro> cachorro = doguinhoRepo.findById(idCachorro);
		if (cachorro.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não será possível apagar esse doguinho pois ele não existe em nosso banco de dados!");
		}
		doguinhoRepo.deleteById(idCachorro);
		return ResponseEntity.noContent().build();
	}

}
