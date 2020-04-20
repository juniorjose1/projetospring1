package br.com.alexandre.domain.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String tipoCliente;
	
	private TipoCliente(int cod, String tipoCliente) {
		this.cod = cod;
		this.tipoCliente = tipoCliente;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getTipoCliente() {
		return tipoCliente;
	}
	
	public static TipoCliente toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(TipoCliente tipoPercorrido: TipoCliente.values()) {
			if(cod.equals(tipoPercorrido.getCod())) {
				return tipoPercorrido;
			}
		}
		
		throw new IllegalArgumentException("ID inválido: " + cod);
	}

}
