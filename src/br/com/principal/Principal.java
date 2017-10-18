package br.com.principal;

import java.math.BigDecimal;

import br.com.controller.ControllerEscopo;
import br.com.escopo.EscopoNumerico;
import br.com.operador.Divisao;
import br.com.operador.IOperador;
import br.com.operador.Multiplicacao;
import br.com.operador.Soma;
import br.com.operador.Subtracao;

public class Principal {
	
	public Principal() {
		String expressao = "( 2 + 2 * ( 2 + 2 ) ) / 2";
		
		ControllerEscopo controllerEscopo = new ControllerEscopo();
		for (String unitario : expressao.split(" ")) {
			if (unitario.matches("[0-9]")) {
				EscopoNumerico escopoNumerico = new EscopoNumerico();
				escopoNumerico.setValor(new BigDecimal(unitario));
				controllerEscopo.setNumero(escopoNumerico);
			} else if (unitario.equals("+")) {
				IOperador operador = new Soma();
				controllerEscopo.setOperador(operador);
			} else if (unitario.equals("-")) {
				IOperador operador = new Subtracao();
				controllerEscopo.setOperador(operador);
			} else if (unitario.equals("*")) {
				IOperador operador = new Multiplicacao();
				controllerEscopo.setOperador(operador);
			} else if (unitario.equals("/")) {
				IOperador operador = new Divisao();
				controllerEscopo.setOperador(operador);
			} else if (unitario.equals("(")) {
				controllerEscopo.abrirEscopoPrioritario();
			} else if (unitario.equals(")")) {
				controllerEscopo.fecharEscopoPrioritario();
			}
		}
		
		BigDecimal close = controllerEscopo.close();
		System.out.println(close.toString());
		
	}

	public static void main(String[] args) {
		new Principal();
	}

}
