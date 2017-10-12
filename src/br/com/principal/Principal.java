package br.com.principal;

import java.util.List;

import br.com.analisador.lexico.AnalisadorLexico;
import br.com.analisador.lexico.Lexico;
import br.com.analisador.lexico.LexicoBuilder;

public class Principal {
	
	public Principal() {
		String codigo = "gequal x = random(2,15)\ngvar y + 2 = 2y\ngres x * y";
		AnalisadorLexico analisadorLexico = new AnalisadorLexico(codigo.split("\n"));
		LexicoBuilder analisar = analisadorLexico.analisar();
		List<Lexico> linhas = analisar.getLinhas();
		
		for (Lexico lexico : linhas) {
			System.out.println("Linha: " + lexico.getLinha() + " tipo " + lexico.getTipo().name());
		}
		
	}

	public static void main(String[] args) {
		new Principal();
	}

}
