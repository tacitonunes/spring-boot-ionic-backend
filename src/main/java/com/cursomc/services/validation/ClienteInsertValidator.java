package com.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.cursomc.domain.Cliente;
import com.cursomc.domain.enums.TipoCliente;
import com.cursomc.dto.NewClienteDTO;
import com.cursomc.respositories.ClienteRepository;
import com.cursomc.services.exceptions.FieldMessage;
import com.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, NewClienteDTO> {

	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {}
	
	@Override
	public boolean isValid(NewClienteDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !BR.isValidCPF(objDTO.getNi())) {
			list.add(new FieldMessage("ni", "CPF Inválido"));
		}
		
		if(objDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !BR.isValidCNPJ(objDTO.getNi())) {
			list.add(new FieldMessage("ni", "CNPJ Inválido"));
		}
		
		Cliente aux = repo.findByEmail(objDTO.getEmail());
		if (aux != null) {	list.add(new FieldMessage("email", "E-mail já cadastrado!"));	}
		
		for (FieldMessage e: list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}

}
