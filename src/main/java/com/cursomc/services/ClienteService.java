package com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Cliente;
import com.cursomc.dto.ClienteDTO;
import com.cursomc.respositories.ClienteRepository;
import com.cursomc.services.exceptions.IntegridadeDadosException;
import com.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public List<Cliente> listAll() {
		//List<Cliente> clientes = repo.findAll();
		return repo.findAll();
	}

	public Cliente findById(Integer id) {
		
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(
					() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())
				);
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setEmail(obj.getEmail());
		newObj.setNome(obj.getNome());
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException err) {
			throw new IntegridadeDadosException("Não é possível excluir um cliente porque há entidades relacionadas.");
		}
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageReq = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageReq);
	}
	
	public Cliente fromDTO (ClienteDTO objDTO) {
		//throw new UnsupportedOperationException();
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	
}
