package com.diegomeruoca.osteste.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diegomeruoca.osteste.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	List<Cliente> findByNome(String nome); //Filtrar por nome
	List<Cliente> findByNomeContaining(String nome);//Filtar por nome que contenha ...
}
