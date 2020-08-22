package com.diegomeruoca.osteste.domain.service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegomeruoca.osteste.domain.exception.NegocioException;
import com.diegomeruoca.osteste.domain.model.Cliente;
import com.diegomeruoca.osteste.domain.model.OrdemServico;
import com.diegomeruoca.osteste.domain.model.StatusOredemServico;
import com.diegomeruoca.osteste.domain.repository.ClienteRepository;
import com.diegomeruoca.osteste.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public OrdemServico criar(OrdemServico ordemServico) {
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado!"));
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOredemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
			
		return ordemServicoRepository.save(ordemServico);
	}
	
}
