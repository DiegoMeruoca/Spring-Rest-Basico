package com.diegomeruoca.osteste.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegomeruoca.osteste.domain.exception.NegocioException;
import com.diegomeruoca.osteste.domain.model.Cliente;
import com.diegomeruoca.osteste.domain.repository.ClienteRepository;

@Service //Defino como serviço do Spring para tornar disponível para outras classes...
public class CadastroClienteService {
	
	@Autowired
	private ClienteRepository clientRepository;
	
	/*Ao ser executado o metodo de cadastro no controller sera passado pra este método, 
	onde posteriormente podemos incluir nossas regras de negocio. Desta maneira, os dados 
	serão alterados no bd atraves apenas desta classe */
	public Cliente salvar(Cliente cliente) {
		Cliente emailCliente = clientRepository.findByEmail(cliente.getEmail());
		//Se já existe o email cadastrado e o e-mail é de um cliente diferente do que estou atualizando
		if (emailCliente != null && !emailCliente.equals(cliente)) {
			throw new NegocioException("Este e-mail ja pertence a outro cliente cadastrado!");
		}
		
		return clientRepository.save(cliente);
	}
	
	public void excluir(Long clienteId) {
		clientRepository.deleteById(clienteId);
	}

}
