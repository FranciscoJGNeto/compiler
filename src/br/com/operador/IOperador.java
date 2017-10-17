package br.com.operador;

import java.math.BigDecimal;

public interface IOperador {
	
	BigDecimal execute(BigDecimal esquerda, BigDecimal direita);
	
	Integer getPrioridade();

}
