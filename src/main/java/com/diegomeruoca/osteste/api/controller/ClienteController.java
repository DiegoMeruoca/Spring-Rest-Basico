package com.diegomeruoca.osteste.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diegomeruoca.osteste.domain.model.Cliente;

@RestController
public class ClienteController {
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		var cliente1 = new Cliente();
		var cliente2 = new Cliente();
		
		cliente1.setId(1L);//Necessita do L por se tratar de long
		cliente1.setNome("João Pedro Mendes");
		cliente1.setEmail("jp.mendes@gmail.com");
		cliente1.setTelefone("(11) 4615-4548");
		
		cliente2.setId(2L);//Necessita do L por se tratar de long
		cliente2.setNome("Marina Pereira");
		cliente2.setEmail("marina.pereira@gmail.com");
		cliente2.setTelefone("(11) 4518-7518");
		
		return Arrays.asList(cliente1, cliente2);
	}

}
