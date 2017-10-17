package br.com.principal;

import java.math.BigDecimal;

import br.com.escopo.EscopoBinario;
import br.com.escopo.EscopoNumerico;
import br.com.operador.Divisao;
import br.com.operador.IOperador;
import br.com.operador.Multiplicacao;
import br.com.operador.Soma;
import br.com.operador.Subtracao;

public class Principal {
	
	public Principal() {
		String expressao = "5 * ( 2 + ( 8 / 2 ) )";
		
		EscopoBinario escopo = new EscopoBinario();
		for (String unitario : expressao.split(" ")) {
			if (unitario.matches("[0-9]")) {
				EscopoNumerico escopoNumerico = new EscopoNumerico();
				escopoNumerico.setValor(new BigDecimal(unitario));
				escopo.setEscopo(escopoNumerico);
			} else if (unitario.equals("+")) {
				IOperador operador = new Soma();
				escopo.setOperador(operador);
			} else if (unitario.equals("-")) {
				IOperador operador = new Subtracao();
				escopo.setOperador(operador);
			} else if (unitario.equals("*")) {
				IOperador operador = new Multiplicacao();
				escopo.setOperador(operador);
			} else if (unitario.equals("/")) {
				IOperador operador = new Divisao();
				escopo.setOperador(operador);
			} else if (unitario.equals("(")) {
				escopo.createEscopoParenteses();
			} else if (unitario.equals(")")) {
				escopo.fecharEscopoParenteses();
			}
		}
		
		BigDecimal close = escopo.close();
		System.out.println(close.toString());
		
	}

	public static void main(String[] args) {
		new Principal();
	}

}
