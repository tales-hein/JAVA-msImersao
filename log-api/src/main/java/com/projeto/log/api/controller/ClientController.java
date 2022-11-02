package com.projeto.log.api.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.log.domain.model.Client;
import com.projeto.log.domain.repository.ClientRepository;


@RestController
@RequestMapping("/clientes")
public class ClientController {
	
	//Uma alternativa para o autowired é gerar o construtor
	//do clientRepository
	@Autowired
	private ClientRepository clientRepository;
	
	@GetMapping
	public List<Client> listar() {
		return clientRepository.findAll();
	}
	
	@GetMapping("/{clientId}")
	public ResponseEntity<Client> buscar(@PathVariable Long clientId) {
		return clientRepository.findById(clientId)
				.map(client -> ResponseEntity.ok(client))
				.orElse(ResponseEntity.notFound().build());
		
		//Optional<Client> client = clientRepository.findById(clientId);
		//if(client.isPresent()) {
		//	return ResponseEntity.ok(client.get());
		//} else {
		//	return ResponseEntity.notFound().build();
		//}
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Client adicionar(@RequestBody Client client) {
		return clientRepository.save(client);
	}
	
	@PutMapping("/{clientId}")
	public ResponseEntity<Client> atualizar(@PathVariable Long clientId, @RequestBody Client client){
		if(!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		} else {
			client.setId(clientId);
			//com o id "setado" o JPA reconhece que o 
			// save sera na verdade um update
			client = clientRepository.save(client);
			return ResponseEntity.ok(client);
		}
	}
	
	@DeleteMapping("/{clientId}")
	public ResponseEntity<Void> excluir(@PathVariable Long clientId){
		if(!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		} else {
			clientRepository.deleteById(clientId);
			return ResponseEntity.noContent().build();
		}
		
	}
}
