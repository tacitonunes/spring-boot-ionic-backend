package com.cursomc.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	/*
	 * O RESTO É IGUAL AO TIPOCLIENTE
	 */
	
	private Integer codigo;
	private String descricao;
	
	private EstadoPagamento(Integer cod, String desc) {
		this.codigo = cod;
		this.descricao = desc;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamento toEnum(Integer cod) {
		
		if(cod == null) { return null; }
		
		for (EstadoPagamento x : EstadoPagamento.values()) {
			
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
		
	}
	
}
