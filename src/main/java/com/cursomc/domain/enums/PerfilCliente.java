package com.cursomc.domain.enums;

public enum PerfilCliente {

	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	/*
	 * O RESTO É IGUAL AO TIPOCLIENTE
	 */
	
	private Integer codigo;
	private String descricao;
	
	private PerfilCliente(Integer cod, String desc) {
		this.codigo = cod;
		this.descricao = desc;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static PerfilCliente toEnum(Integer cod) {
		
		if(cod == null) { return null; }
		
		for (PerfilCliente x : PerfilCliente.values()) {
			
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
		
	}
	
}
