package com.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

//4 - implementar o serializable

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	//5 - Mapear a tabela do banco de dados com o "@Entity" e conforme abaixo: @Id e @GeneratedValue
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Double preco;
	
	/* 6 - Mapeando o relacionamento muitos para muitos com Categorias (Necessário apenas em um dos lados do relacionamento)
	 * Para o @JoinTable(
	 * 				name=<nome_da_tabela_relacional>, 
	 * 				joinColumns = @JoinColumn(<nome_do_campo_da_tabela_desta_classe>), 
	 * 				inverseJoinColumns = @JoinColumn(name="<nome_do_campo_da_tabela_da_outra_classe>")
	 * 				)
	 * Após realizado o mapamento aqui na classe, ir à outra classe (outro lado do relacionamento), e inserir:
	 * 			@ManyToMany(mappedBy="<nome_atributo_desta_classe_mapeado_com_a_outra>")
	 * Neste caso, ir em Produto e inserir:
	 * 		@ManyToMany(mappedBy="categorias")
	 * 			
	 */
	
	/*Evitar Serialização Cíclica!!
	 * O carinha abaixo fica no lado em quê você não quer que sejam listados os outros objetos da outra classe
	 */
	@JsonBackReference
	@ManyToMany
	@JoinTable(
			name = "PRODUTO_CATEGORIA", 
			joinColumns = @JoinColumn(name="produto_id"), 
			inverseJoinColumns = @JoinColumn(name="categoria_id")
			)	
	
	//1 produto possui várias categorias
	/* |*| */ private List<Categoria> categorias = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="id.produto")
	private Set<ItemPedido> itens = new HashSet<>();
	
	//2 Gerar os construtores da Classe
	public Produto() {}

	//source >> generate constructor using fields (sem categoria, inicializada na linha |*|)
	public Produto(Integer id, String nome, Double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
	
	@JsonIgnore
	public List<Pedido> getPedidos(){
		List<Pedido> pedidos = new ArrayList<>();
		for (ItemPedido x : itens) {
			pedidos.add(x.getPedido());
		}
		return pedidos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	public Set<ItemPedido> getItens() {
		return itens;
	}
	
	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}
	
	//3 - Gerar hashCode e Equals
	// Source >> Generate hashcode e equals >> marcar apenas "id"
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
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}
	
}
