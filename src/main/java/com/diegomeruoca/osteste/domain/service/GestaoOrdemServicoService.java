package com.diegomeruoca.osteste.domain.service;

import java.time.OffsetDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.diegomeruoca.osteste.api.model.Comentario;
import com.diegomeruoca.osteste.domain.exception.NegocioException;
import com.diegomeruoca.osteste.domain.model.Cliente;
import com.diegomeruoca.osteste.domain.model.OrdemServico;
import com.diegomeruoca.osteste.domain.model.StatusOredemServico;
import com.diegomeruoca.osteste.domain.repository.ClienteRepository;
import com.diegomeruoca.osteste.domain.repository.ComentarioRepository;
import com.diegomeruoca.osteste.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	public OrdemServico criar(OrdemServico ordemServico) {
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado!"));
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOredemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
			
		return ordemServicoRepository.save(ordemServico);
	}
	
	public void finalizar (Long ordemServicoId) {
		OrdemServico ordemServico = buscar(ordemServicoId);
		ordemServico.finalizar();
		ordemServicoRepository.save(ordemServico);
	}
	
	public Comentario adicionarComentario(Long ordemServicoId, String descricao){
		OrdemServico ordemServico = buscar(ordemServicoId);
		
		Comentario comentario = new Comentario();
		comentario.setDataEnvio(OffsetDateTime.now());
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);
		
		return comentarioRepository.save(comentario);
	}

	private OrdemServico buscar(Long ordemServicoId) {
		return ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new NegocioException("Ordem de Serviço não encontrada!"));
	}
}
