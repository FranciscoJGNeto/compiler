package br.com.operador;

import java.math.BigDecimal;

public abstract class IOperador {
	
	public abstract BigDecimal execute(BigDecimal esquerda, BigDecimal direita);
	
	public abstract Integer getPrioridade();

}
