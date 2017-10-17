package br.com.operador;

import java.math.BigDecimal;

public class Subtracao implements IOperador {

	@Override
	public BigDecimal execute(BigDecimal esquerda, BigDecimal direita) {
		return esquerda.subtract(direita);
	}
	
	@Override
	public Integer getPrioridade() {
		return 0;
	}

}
