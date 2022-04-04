package com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Pedido;
import com.cursomc.respositories.PedidoRepository;
import com.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public List<Pedido> listAll() {
		//List<Pedido> categorias = repo.findAll();
		return repo.findAll();
	}

	public Pedido findById(Integer id) {

		/*
		 * Pedido obj = repo.getById(id);
		 * return obj;
		*/
		
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())
				);
		
		
	}
	
}
