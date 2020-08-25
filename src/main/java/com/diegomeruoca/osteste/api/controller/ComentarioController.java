package com.diegomeruoca.osteste.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.diegomeruoca.osteste.api.model.Comentario;
import com.diegomeruoca.osteste.api.model.ComentarioInput;
import com.diegomeruoca.osteste.api.model.ComentarioModel;
import com.diegomeruoca.osteste.domain.exception.NegocioException;
import com.diegomeruoca.osteste.domain.model.OrdemServico;
import com.diegomeruoca.osteste.domain.repository.OrdemServicoRepository;
import com.diegomeruoca.osteste.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {
	
	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServicoService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private OrdemServicoRepository ordemServidoRepository;
	
	@GetMapping
	private List<ComentarioModel> listar(@PathVariable Long ordemServicoId ){
		OrdemServico ordemServico = ordemServidoRepository.findById(ordemServicoId).
				orElseThrow(() -> new NegocioException("Ordem de Serviço não encontrada!"));
		
		return toCollectionModel(ordemServico.getComentarios());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioModel adicionar(@PathVariable Long ordemServicoId,
			@Valid @RequestBody ComentarioInput comentarioInput) {
		Comentario comentario = gestaoOrdemServicoService.adicionarComentario(ordemServicoId,
				comentarioInput.getDescricao());
		return toModel(comentario);
	}
	
	//Transforma Comentario em ComentarioModel
	private ComentarioModel toModel(Comentario comentario) {
		return modelMapper.map(comentario, ComentarioModel.class);
	}
	
	//Transforma Lista de Comentario em Lista ComentarioModel
	private List<ComentarioModel> toCollectionModel(List<Comentario> listComentario) {
		return listComentario.stream().map(comentario -> toModel(comentario)).
				collect(Collectors.toList());
	}
}
