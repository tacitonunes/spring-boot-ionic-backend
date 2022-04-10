package com.cursomc.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursomc.domain.ItemPedido;
import com.cursomc.domain.PagamentoComBoleto;
import com.cursomc.domain.Pedido;
import com.cursomc.domain.enums.EstadoPagamento;
import com.cursomc.respositories.ItemPedidoRepository;
import com.cursomc.respositories.PagamentoRepository;
import com.cursomc.respositories.PedidoRepository;
import com.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService bolServ;
	
	@Autowired
	private PagamentoRepository pgtoRepo;
	
	@Autowired
	private ProdutoService prodServ;
	
	@Autowired
	private ItemPedidoRepository ipRepo;

	@Autowired
	private ClienteService clienteServ;
	
	public List<Pedido> listAll() {
		//List<Pedido> categorias = repo.findAll();
		return repo.findAll();
	}

	public Pedido findById(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())
				);		
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteServ.findById(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgto = (PagamentoComBoleto) obj.getPagamento();
			bolServ.preencherPagamentoComBoleto(pgto, obj.getInstante());
		}
		//Salvar o pedido
		obj = repo.save(obj);
		//Salvar o pagamento
		pgtoRepo.save(obj.getPagamento());
		
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(prodServ.findById(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);			
		}
		
		ipRepo.saveAll(obj.getItens());
		System.out.println(obj); // Colocando o obj dentro do sysout, automaticamente será chamado o obj.toString()
		return obj;	
		
	}
	
}
