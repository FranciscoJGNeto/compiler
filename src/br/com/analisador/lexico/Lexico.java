package br.com.analisador.lexico;

public class Lexico {
	
	private String linha;
	
	private int numeroLinha;
	
	private TipoExecucaoLexica tipo;

	public String getLinha() {
		return linha;
	}

	public void setLinha(String linha) {
		this.linha = linha;
	}

	public int getNumeroLinha() {
		return numeroLinha;
	}

	public void setNumeroLinha(int numeroLinha) {
		this.numeroLinha = numeroLinha;
	}

	public TipoExecucaoLexica getTipo() {
		return tipo;
	}

	public void setTipo(TipoExecucaoLexica tipo) {
		this.tipo = tipo;
	}

}
