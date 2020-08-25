package com.diegomeruoca.osteste.api.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
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

import com.diegomeruoca.osteste.domain.model.Cliente;
import com.diegomeruoca.osteste.domain.repository.ClienteRepository;
import com.diegomeruoca.osteste.domain.service.CadastroClienteService;

@RestController
@RequestMapping("/clientes") //Define a URL que o controlador responde
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CadastroClienteService cadastroClienteService;
	
	@GetMapping //Executado ao dar Request GET n URL padrão
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{clienteId}") //Executado ao dar Request GET na URL padrão + /{id}
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId){
		Optional<Cliente> cliente = clienteRepository.findById(clienteId); 
		//Optional é utilizado pois a variavel pode ou não receber um cliente após a busca
		
		if (cliente.isPresent()) {//Caso encontre o cliente
			return ResponseEntity.ok(cliente.get());//Retorna a resposta HTTP ok (200) e o cliente
		}
		
		return ResponseEntity.notFound().build(); //Caso contrário responde não encontrado (404)
	}
	
	@PostMapping //Executado ao dar Request POST na URL padrão
	@ResponseStatus(HttpStatus.CREATED)//Pra responder o status 201 - Created
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {//@RequestBody transforma o json vondo no body em um obj Java
		return cadastroClienteService.salvar(cliente);
	}
	
	@PutMapping("/{clienteId}") //Executado ao dar Request PUT na URL padrão + /{id}
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId,
			@RequestBody Cliente cliente){
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId);//Adiciona o is no obj
		cliente = cadastroClienteService.salvar(cliente);
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{clienteId}") //Executado ao dar Request DELETE na URL padrão + /{id}
	public ResponseEntity<Void> excluir(@PathVariable Long clienteId){
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cadastroClienteService.excluir(clienteId);
		return ResponseEntity.noContent().build();//Retorna noContent (204)
	}
	
}
