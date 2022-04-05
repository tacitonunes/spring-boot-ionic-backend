package com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Categoria;
import com.cursomc.dto.CategoriaDTO;
import com.cursomc.respositories.CategoriaRepository;
import com.cursomc.services.exceptions.IntegridadeDadosException;
import com.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public List<Categoria> listAll() {
		//List<Categoria> categorias = repo.findAll();
		return repo.findAll();
	}

	public Categoria findById(Integer id) {
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
	
	public Categoria update(Categoria upCat) {
		Categoria dbCat = findById(upCat.getId());
		updateData(dbCat, upCat);
		return repo.save(dbCat);
	}
	
	private void updateData(Categoria dbCat, Categoria upCat) {
		dbCat.setNome(upCat.getNome());
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException err) {
			throw new IntegridadeDadosException("Não é possível excluir uma categoria que possui produtos.");
		}
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageReq = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageReq);
	}
	
	public Categoria fromDTO (CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}
	
}
