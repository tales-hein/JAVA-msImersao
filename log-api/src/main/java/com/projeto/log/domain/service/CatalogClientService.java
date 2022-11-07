package com.projeto.log.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.log.domain.exception.DomainException;
import com.projeto.log.domain.model.Client;
import com.projeto.log.domain.repository.ClientRepository;

@Service
public class CatalogClientService {
	private ClientRepository clientRepository;

	public CatalogClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	
	@Transactional
	public Client salvar(Client client) {
		boolean emailExists = clientRepository.findByEmail(client.getEmail()).stream().anyMatch(c -> !c.equals(client));
		if(emailExists) {
			throw new DomainException("Já existe um cliente cadastrado com este e-mail.");
		}else{
			return clientRepository.save(client);
		}
	}
	
	@Transactional
	public void excluir(Long clientId) {
		clientRepository.deleteById(clientId);
	}
	
	public Client buscar(Long clientId) {
		return clientRepository.findById(clientId)
				.orElseThrow(()-> new DomainException("Cliente não encontrado."));
	}
}
