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
		Cachorro cachorro = doguinhoRepo.findById(idCachorro).get();
		return ResponseEntity.status(HttpStatus.OK).body(cachorro);
	}
	
	@PostMapping("/cachorros")
	public ResponseEntity<Cachorro> SaveCachorro(@RequestBody Cachorro cachorro) {
		Cachorro ct = doguinhoRepo.save(cachorro);
		return ResponseEntity.status(HttpStatus.CREATED).body(ct);
	}
	
	@PutMapping("/cachorros/{idcachorro}")
	public ResponseEntity<Cachorro> alterarContato(@PathVariable("idcachorro") Long idCachorro, @RequestBody Cachorro cachorro) {
		cachorro.setId(idCachorro);
		return ResponseEntity.ok(doguinhoRepo.save(cachorro));
	}
	
	@DeleteMapping("/cachorros/{idcachorro}")
	public ResponseEntity<Void> deleteContato(@PathVariable("idcachorro") Long idCachorro) {
		doguinhoRepo.deleteById(idCachorro);
		return ResponseEntity.noContent().build();
	}

}
