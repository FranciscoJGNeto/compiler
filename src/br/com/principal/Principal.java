package br.com.principal;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.controller.ControllerEscopo;
import br.com.escopo.EscopoNumerico;
import br.com.operador.Divisao;
import br.com.operador.IOperador;
import br.com.operador.Multiplicacao;
import br.com.operador.Soma;
import br.com.operador.Subtracao;

public class Principal {
	
	private Pattern decimalPattern = Pattern.compile("([0-9].*)(\\, || \\.)([0-9].*)");
	
	public Principal() {
		String expressao = "554 + 6";
		
		ControllerEscopo controllerEscopo = new ControllerEscopo();
		for (String unitario : expressao.split(" ")) {
			// Matcher para validar números decimais.
			Matcher decimalMatcher = decimalPattern.matcher(unitario);
			
			// Verifica se é um número
			if (unitario.matches("[0-9]+") || decimalMatcher.find()) {
				EscopoNumerico escopoNumerico = new EscopoNumerico();
				unitario = unitario.replace(",", ".");
				escopoNumerico.setValor(new BigDecimal(unitario));
				controllerEscopo.setNumero(escopoNumerico);
				
			// Verifica se é uma soma.
			} else if (unitario.equals("+")) {
				IOperador operador = new Soma();
				controllerEscopo.setOperador(operador);
				
			// Verifica se é uma subtração.
			} else if (unitario.equals("-")) {
				IOperador operador = new Subtracao();
				controllerEscopo.setOperador(operador);
				
			// Verifica se é uma multiplicação.
			} else if (unitario.equals("*")) {
				IOperador operador = new Multiplicacao();
				controllerEscopo.setOperador(operador);
				
			// Verifica se é uma divisão.
			} else if (unitario.equals("/")) {
				IOperador operador = new Divisao();
				controllerEscopo.setOperador(operador);
				
			// Verifica se é para abrir escopo.
			} else if (unitario.equals("(") && unitario.equals("[") && unitario.equals("{")) {
				controllerEscopo.abrirEscopoPrioritario();
			
			// Verifica se é para fechar escopo.
			} else if (unitario.equals(")") && unitario.equals("]") && unitario.equals("}")) {
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
