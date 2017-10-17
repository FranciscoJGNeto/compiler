package br.com.operador;

import java.math.BigDecimal;

public class Divisao implements IOperador {

	@Override
	public BigDecimal execute(BigDecimal esquerda, BigDecimal direita) {
		return esquerda.divide(direita, BigDecimal.ROUND_HALF_UP);
	}
	
	@Override
	public Integer getPrioridade() {
		return 1;
	}

}
