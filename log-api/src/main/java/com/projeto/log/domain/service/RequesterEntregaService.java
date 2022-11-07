package com.projeto.log.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.log.domain.model.Client;
import com.projeto.log.domain.model.Entrega;
import com.projeto.log.domain.model.StatusEntrega;
import com.projeto.log.domain.repository.EntregaRepository;

@Service
public class RequesterEntregaService {
	@Autowired
	private EntregaRepository entregaRepository;
	@Autowired
	private CatalogClientService catalogClientService;
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		Client client = catalogClientService.buscar(entrega.getClient().getId());
		entrega.setClient(client);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(LocalDateTime.now());
		return entregaRepository.save(entrega);
	}
}
