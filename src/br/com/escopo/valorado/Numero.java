package br.com.escopo.valorado;

import java.math.BigDecimal;

import br.com.escopo.IEscopoValorado;

public class Numero implements IEscopoValorado {

	private BigDecimal valor;
	
	public Numero(BigDecimal valor) {
		this.valor = valor;
	}
	
	@Override
	public BigDecimal gerarValor() {
		return this.valor;
	}

}
