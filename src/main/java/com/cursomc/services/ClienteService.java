package com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursomc.domain.Cidade;
import com.cursomc.domain.Cliente;
import com.cursomc.domain.Endereco;
import com.cursomc.domain.enums.TipoCliente;
import com.cursomc.dto.ClienteDTO;
import com.cursomc.dto.NewClienteDTO;
import com.cursomc.respositories.ClienteRepository;
import com.cursomc.respositories.EnderecoRepository;
import com.cursomc.services.exceptions.IntegridadeDadosException;
import com.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository endRepo;
	
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
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		endRepo.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente upCli) {
		Cliente dbCli = findById(upCli.getId());
		updateData(dbCli, upCli);
		return repo.save(dbCli);
	}
	
	private void updateData(Cliente dbCli, Cliente upCli) {
		dbCli.setEmail(upCli.getEmail());
		dbCli.setNome(upCli.getNome());
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
	
	public Cliente fromDTO (NewClienteDTO objDTO) {
		Cliente cli =  new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getNi(), TipoCliente.toEnum(objDTO.getTipo()));
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		if(objDTO.getTelefone2() != null) {cli.getTelefones().add(objDTO.getTelefone2());}
		if(objDTO.getTelefone3() != null) {cli.getTelefones().add(objDTO.getTelefone3());}
		return cli;
	}
	
}
