package br.com.operador;

import java.math.BigDecimal;

public interface IOperador {
	
	BigDecimal executa();
	
	Prioridade prioridade = Prioridade.NIVEL1;
}
