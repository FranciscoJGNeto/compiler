package br.com.controller;

import java.math.BigDecimal;

import br.com.escopo.EscopoBinario;
import br.com.escopo.EscopoNumerico;
import br.com.escopo.EscopoTipo;
import br.com.operador.IOperador;

public class ControllerEscopo {
	
	private StackEscopo<EscopoBinario> escopoBinario;
	
	public ControllerEscopo() {
		this.escopoBinario = new StackEscopo<EscopoBinario>();
		EscopoBinario escopoBinario = new EscopoBinario();
		escopoBinario.setTipo(EscopoTipo.NORMAL);
		this.escopoBinario.push(escopoBinario);
	}
	
	public void setNumero(EscopoNumerico numero) {
		this.escopoBinario.currentElement().setEscopo(numero);
		if (EscopoTipo.PRIORITARIO.equals(this.escopoBinario.currentElement().getTipo())) {
			fecharEscopoPrioritario();
		}
	}
	
	public void setOperador(IOperador operador) {
		if (this.escopoBinario.currentElement().haveOperador()) {
			EscopoBinario escopoBinario = new EscopoBinario();
			if (operador.getPrioridade() > this.escopoBinario.currentElement().getOperador().getPrioridade()) {
				escopoBinario.setTipo(EscopoTipo.PRIORITARIO);
				escopoBinario.setEscopo(this.escopoBinario.currentElement().popEscopo());
//				this.escopoBinario.currentElement().setEscopo(escopoBinario);
			} else {
				escopoBinario.setTipo(EscopoTipo.NORMAL);
				escopoBinario.setEscopo(this.escopoBinario.pop());
			}
			
			this.escopoBinario.push(escopoBinario);
		}
		
		this.escopoBinario.currentElement().setOperador(operador);
	}
	
	public void abrirEscopoPrioritario() {
		EscopoBinario escopoBinario = new EscopoBinario();
		escopoBinario.setTipo(EscopoTipo.PARENTESES);
		this.escopoBinario.push(escopoBinario);
	}
	
	public void fecharEscopoPrioritario() {
		EscopoBinario last = this.escopoBinario.pop();
		EscopoBinario currentElement = this.escopoBinario.currentElement();
		currentElement.setEscopo(last);
	}
	
	public BigDecimal close() {
//		while (escopoBinario.size() > 1) {
//			fecharEscopoPrioritario();
//		}
		
		return escopoBinario.pop().close();
	}

}
