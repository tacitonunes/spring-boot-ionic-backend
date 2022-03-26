package com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Cliente;
import com.cursomc.respositories.ClienteRepository;
import com.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public List<Cliente> listar() {
		//List<Cliente> categorias = repo.findAll();
		return repo.findAll();
	}

	public Cliente buscar(Integer id) {

		/*
		 * Cliente obj = repo.getById(id);
		 * return obj;
		*/
		
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())
				);
		
		
	}
	
}
