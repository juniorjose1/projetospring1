package br.com.alexandre.domain.enums;

public enum EstadoPagamento {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private Integer codigo;
	private String descricao;
	
	private EstadoPagamento(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamento toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(EstadoPagamento estadoPercorrido: EstadoPagamento.values()) {
			if(cod.equals(estadoPercorrido.getCodigo())) {
				return estadoPercorrido;
			}
		}
		
		throw new IllegalArgumentException("Código Inválido: " + cod);
	}
	
	
}
