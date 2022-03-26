package com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Categoria;
import com.cursomc.respositories.CategoriaRepository;
import com.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public List<Categoria> listar() {
		//List<Categoria> categorias = repo.findAll();
		return repo.findAll();
	}

	public Categoria buscar(Integer id) {
		/*
		 * Categoria obj = repo.getById(id);
		 * return obj;
		*/
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())
				);
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
}
