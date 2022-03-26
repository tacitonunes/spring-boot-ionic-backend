package com.cursomc.domain.enums;

public enum TipoCliente {
	
	/*
	 * NO TIPO ENUMERADO, A IMPLEMENTAÇÃO MAIS BÁSICA É COLOCAR OS VALORES SERPADOS POR VÍRGULA;
	 * DA FORMA COMO ABAIXO, QUANDO MAPEARMOS NO JPA, ELE SALVA, OU O NOME EM FORMA DE STRING OU NÚMERO INTEIRO, COMEÇANDO POR 0 (ZERO)
	 */
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private Integer codigo;
	private String descricao;
	
	private TipoCliente(Integer cod, String desc) {
		this.codigo = cod;
		this.descricao = desc;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer cod) {
		
		if(cod == null) { return null; }
		
		for (TipoCliente x : TipoCliente.values()) {
			
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
		
	}
	
}
