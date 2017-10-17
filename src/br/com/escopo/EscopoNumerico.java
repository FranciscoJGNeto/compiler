package br.com.escopo;

import java.math.BigDecimal;

public class EscopoNumerico implements IEscopo {

	private BigDecimal valor;

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public BigDecimal close() {
		return valor;
	}

}
