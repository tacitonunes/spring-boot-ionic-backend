package com.cursomc.domain;

import javax.persistence.Entity;

import com.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento {
	
	private static final long serialVersionUID = 1L;
	
	private Integer nrParcelas;

	public PagamentoComCartao() {}

	//SOURCE >> GENERATE CONSTRUCTOR FROM SUPERCLASS E ADICIONO OS ATRIBUTOS DELE MESMO...
	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer nrParcelas) {
		super(id, estado, pedido);
		this.nrParcelas = nrParcelas;
	}

	public Integer getNrParcelas() {
		return nrParcelas;
	}

	public void setNrParcelas(Integer nrParcelas) {
		this.nrParcelas = nrParcelas;
	}
	
	//NAS SUBCLASSES NÃO É NECESSÁRIO O HASHCODE E EQUALS, POIS A COMPARAÇÃO POR ID É FEITO NA SUPERCLASSE (PAGAMENTO)
	
	//NAS SUBCLASSES TAMBÉM NÃO É NECESSÁRIO IMPLEMENTAR O SERIALIZABLE
}
