package br.com.operador.aritimetico;

import java.math.BigDecimal;

import br.com.escopo.IEscopoValorado;
import br.com.operador.IOperador;

public class Soma implements IOperador {

	private IEscopoValorado esquerda;
	private IEscopoValorado direita;
	
	public Soma(IEscopoValorado esquerda, IEscopoValorado direita) {
		this.esquerda = esquerda;
		this.direita = direita;
	}
	
	@Override
	public BigDecimal executa() {
		return this.esquerda.gerarValor().add(this.direita.gerarValor());
	}
}
