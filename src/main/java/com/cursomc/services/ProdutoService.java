package com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Categoria;
import com.cursomc.domain.Produto;
import com.cursomc.respositories.CategoriaRepository;
import com.cursomc.respositories.ProdutoRepository;
import com.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;

	@Autowired
	private CategoriaRepository catRepo;
	
	public List<Produto> listAll() {
		//List<Produto> categorias = repo.findAll();
		return repo.findAll();
	}

	public Produto findById(Integer id) {

		/*
		 * Produto obj = repo.getById(id);
		 * return obj;
		*/
		
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName())
				);
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageReq = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = catRepo.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageReq);
		//OOUUU 
		//return repo.search(nome, categorias, pageReq);
		
		
	}
}