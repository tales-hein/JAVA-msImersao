package com.projeto.log.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.log.domain.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	List<Client> findByNome(String nome);
	List<Client> findByNomeContaining(String nome);
	Optional<Client> findByEmail(String email);
}
