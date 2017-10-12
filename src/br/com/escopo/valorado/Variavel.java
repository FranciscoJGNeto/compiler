package br.com.escopo.valorado;

import java.math.BigDecimal;

import br.com.escopo.IEscopoValorado;

public class Variavel implements IEscopoValorado {

	private String variavel;
	private BigDecimal valor;
	
	public Variavel(String variavel) {
		this.variavel = variavel;
	}
	
	public void setValorVariavel(BigDecimal valor) {
		this.valor = valor;
	}
	
	public String getVariavel() {
		return this.variavel;
	}

	@Override
	public BigDecimal gerarValor() {
		return this.valor;
	}

}
