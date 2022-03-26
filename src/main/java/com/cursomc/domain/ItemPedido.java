package com.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ItemPedido implements Serializable { 
	
	private static final long serialVersionUID = 1L;

	//CLASSE DE ASSOCIAÇÃO ENTRE PRODUTO X PEDIDO
	
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();
	
	private Double desconto;
	private Double preco;
	private Integer quantidade;
	
	/* AS ASSOCIAÇÕES DESTA CLASSE JÁ FORAM FEITAS POR INTERMÉDIO DO ITEMPEDIDOPK,
	 * PORÉM PRECISAMOS FAZER A CLASSE PEDIDO CONHECER OS ITEMS DE PEDIDO QUE CONSTAM NELE.
	 * PARA ISSO, CRIAREMOS UM CONJUNTO SEM REPETIÇÃO (SET) DE ITEMPEDIDO
	 */
	
	public ItemPedido() {}
	
	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Double preco, Integer quantidade) {
		super();
		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.preco = preco;
		this.quantidade = quantidade;
	}
	
	public Pedido getPedido() {
		return id.getPedido();
	}
	
	public Produto getProduto() {
		return id.getProduto();
	}

	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
