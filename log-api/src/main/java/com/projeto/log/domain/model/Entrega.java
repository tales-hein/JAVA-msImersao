package com.projeto.log.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Entrega {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Client Client;
	
	@Embedded //Abstrair dados do destinatario mas para a tabela entrega.
	private Destinatario destinatario;
	
	private BigDecimal taxa;
	
	@Enumerated(EnumType.STRING)//Tipo da enumeração, na coluna fica a string não a ordinal do enum.
	private StatusEntrega status;
	
	private LocalDateTime dataPedido;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private LocalDateTime dataFinalizacao;
	
	public Entrega() {
	}
	
	public Entrega(Long id, com.projeto.log.domain.model.Client client, Destinatario destinatario, BigDecimal taxa,
			StatusEntrega status, LocalDateTime dataPedido, LocalDateTime dataFinalizacao) {
		super();
		this.id = id;
		Client = client;
		this.destinatario = destinatario;
		this.taxa = taxa;
		this.status = status;
		this.dataPedido = dataPedido;
		this.dataFinalizacao = dataFinalizacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entrega other = (Entrega) obj;
		return Objects.equals(id, other.id);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Client getClient() {
		return Client;
	}
	public void setClient(Client client) {
		Client = client;
	}
	public Destinatario getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(Destinatario destinatario) {
		this.destinatario = destinatario;
	}
	public BigDecimal getTaxa() {
		return taxa;
	}
	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}
	public StatusEntrega getStatus() {
		return status;
	}
	public void setStatus(StatusEntrega status) {
		this.status = status;
	}
	public LocalDateTime getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}
	public LocalDateTime getDataFinalizacao() {
		return dataFinalizacao;
	}
	public void setDataFinalizacao(LocalDateTime dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}
	
}
