package com.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento {
	
	private static final long serialVersionUID = 1L;
	
	private Date dataVencimento;
	private Date dataPagamento;
	
	public PagamentoComBoleto() {}

	//SOURCE >> GENERATE CONSTRUCTOR FROM SUPERCLASS E ADICIONO OS ATRIBUTOS DELE MESMO...
	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	//NAS SUBCLASSES NÃO É NECESSÁRIO O HASHCODE E EQUALS, POIS A COMPARAÇÃO POR ID É FEITO NA SUPERCLASSE (PAGAMENTO)
	
	//NAS SUBCLASSES TAMBÉM NÃO É NECESSÁRIO IMPLEMENTAR O SERIALIZABLE

}
