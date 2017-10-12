package br.com.analisador.lexico;

public class AnalisadorLexico {
	
	private String[] linhas;

	public AnalisadorLexico(String[] linhas) {
		this.linhas = linhas;
	}
	
	public LexicoBuilder analisar() {
		LexicoBuilder lexicoBuilder = new LexicoBuilder();
		for (int i = 0; i < linhas.length; i++) {
			lexicoBuilder.gerar(linhas[i], i);
		}
		
		return lexicoBuilder;
	}

}
