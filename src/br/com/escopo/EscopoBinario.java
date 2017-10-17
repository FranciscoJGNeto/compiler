package br.com.escopo;

import java.math.BigDecimal;

import br.com.operador.IOperador;

public class EscopoBinario implements IEscopo {
	
	private IEscopo direita;
	private IEscopo esquerda;
	private IOperador operador;
	private EscopoBinario prioridade;
	private EscopoBinario prioridadeParenteses;

	public void setOperador(IOperador operador) {
		if (this.prioridadeParenteses != null) {
			this.prioridadeParenteses.setOperador(operador);
		} else if (this.operador == null) {
			this.operador = operador;			
		} else {
			
			// Cria um novo escopo
			EscopoBinario novoEscopo = new EscopoBinario();
			
			if (this.operador.getPrioridade() < operador.getPrioridade()) {
				
				this.prioridade = new EscopoBinario();
				this.prioridade.setEscopo(this.direita);
				this.prioridade.setOperador(operador);
				this.direita = this.prioridade;
				
			} else {
				// Popula as informações desse escopo para o novo escopo.
				novoEscopo.setEscopo(this.esquerda);
				novoEscopo.setOperador(this.operador);
				novoEscopo.setEscopo(this.direita);
				
				// Esvazia as informações desse escopo
				this.direita = null;
				this.prioridade = null;
				
				// Coloca o novo escopo na esquera desse escopo e o novo operador no operador desse escopo.
				this.esquerda = novoEscopo;
				this.operador = operador;
			}
		}
	}
	
	public void setEscopo(IEscopo escopo) {
		if (this.prioridadeParenteses != null) {
			this.prioridadeParenteses.setEscopo(escopo);
		} else if (this.prioridade != null) {
			this.prioridade.setEscopo(escopo);
		} else if (this.esquerda == null) {
			this.esquerda = escopo;
		} else if (this.direita == null) {
			this.direita = escopo;
		} else {
			throw new RuntimeException("Equação com sintaxe incorreta.");			
		}
	}
	
	public void createEscopoParenteses() {
		if (this.prioridadeParenteses == null) {
			this.prioridadeParenteses = new EscopoBinario();			
		} else {
			this.prioridadeParenteses.createEscopoParenteses();
		}
	}
	
	public void fecharEscopoParenteses() {
		EscopoBinario clone = new EscopoBinario();
		clone.esquerda = this.prioridadeParenteses.esquerda;
		clone.operador = this.prioridadeParenteses.operador;
		clone.direita = this.prioridadeParenteses.direita;
		clone.prioridade = this.prioridadeParenteses.prioridade;
		clone.prioridadeParenteses = this.prioridadeParenteses.prioridadeParenteses;
		this.prioridadeParenteses = null;
		this.setEscopo(clone);
	}

	@Override
	public BigDecimal close() {
		
		BigDecimal direita = this.direita.close();
		BigDecimal esquerda = this.esquerda.close();
		return operador.execute(esquerda, direita);
	}

}
