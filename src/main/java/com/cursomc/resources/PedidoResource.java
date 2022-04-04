package com.cursomc.resources;

import java.util.List;

/*import java.util.ArrayList;
import java.util.List;*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cursomc.domain.Pedido;
import com.cursomc.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listAll() {

		List<Pedido> obj = service.listAll();
		return ResponseEntity.ok().body(obj);

	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> findById(@PathVariable Integer id) {
		
		Pedido obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
		
	}
}
