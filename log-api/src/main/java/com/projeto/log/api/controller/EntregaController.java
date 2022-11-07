package com.projeto.log.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.log.domain.model.Entrega;
import com.projeto.log.domain.service.RequesterEntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {
	
	@Autowired
	private RequesterEntregaService requesterEntregaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Entrega solicitar(@RequestBody Entrega entrega) {
		return requesterEntregaService.solicitar(entrega);
	}
}
