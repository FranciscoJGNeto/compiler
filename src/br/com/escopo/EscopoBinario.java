package br.com.escopo;

import java.math.BigDecimal;

import br.com.operador.IOperador;

public class EscopoBinario implements IEscopo {
	
	private IEscopo direita;
	private IEscopo esquerda;
	private IOperador operador;
	private EscopoTipo tipo;

	public EscopoTipo getTipo() {
		return tipo;
	}

	public void setTipo(EscopoTipo tipo) {
		this.tipo = tipo;
	}

	public void setOperador(IOperador operador) {
		this.operador = operador;
	}
	
	public void setEscopo(IEscopo escopo) {
		if (this.esquerda == null) {
			this.esquerda = escopo;
		} else if (this.direita == null) {
			this.direita = escopo;
		} else {
			throw new RuntimeException("Equação com sintaxe incorreta.");
		}
	}
	
	public boolean haveOperador() {
		return this.operador != null;
	}
	
	public IOperador getOperador() {
		return this.operador;
	}
	
	public IEscopo popEscopo() {
		if (this.direita != null) {
			IEscopo direita = this.direita;
			this.direita = null;
			return direita;
		}
		
		IEscopo esquerda = this.esquerda;
		this.esquerda = null;
		return esquerda;
	}

	@Override
	public BigDecimal close() {
		
		if (this.direita == null) {
			return this.esquerda.close();
		}
		
		BigDecimal direita = this.direita.close();			
		BigDecimal esquerda = this.esquerda.close();
		
		return operador.execute(esquerda, direita);
	}

}
