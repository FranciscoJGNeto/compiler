package br.com.operador;

import java.math.BigDecimal;

public class Multiplicacao extends IOperador {

	@Override
	public BigDecimal execute(BigDecimal esquerda, BigDecimal direita) {
		return esquerda.multiply(direita);
	}
	
	@Override
	public Integer getPrioridade() {
		return 1;
	}

}
