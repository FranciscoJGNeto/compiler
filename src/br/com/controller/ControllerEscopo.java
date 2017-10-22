package br.com.controller;

import java.math.BigDecimal;

import br.com.escopo.EscopoBinario;
import br.com.escopo.EscopoNumerico;
import br.com.escopo.EscopoTipo;
import br.com.operador.IOperador;

public class ControllerEscopo {
	
	private StackEscopo<EscopoBinario> escopoBinario;
	
	public ControllerEscopo() {
		// Ao criar o controller, é inicializado uma pilha de controle de escopos binários (pseudo arvore binária).
		this.escopoBinario = new StackEscopo<EscopoBinario>();
		
		// Inicializa um escopo binário, que servirá como base da pilha.
		EscopoBinario escopoBinario = new EscopoBinario();
		escopoBinario.setTipo(EscopoTipo.NORMAL);
		this.escopoBinario.push(escopoBinario);
	}
	
	/**
	 * Coloca um número dentro do escopo que está sendo controlado (último escopo da pilha).
	 * @param numero
	 */
	public void setNumero(EscopoNumerico numero) {
		this.escopoBinario.currentElement().setEscopo(numero);
	}
	
	/**
	 * Coloca um operador de cálculo dentro do escopo que está sendo controlado.<br>
	 * Caso o escopo já possua um operador são feitos duas validações.<br>
	 * <p>1ª: Verifica se o novo operador tem prioridade maior que o operador do escopo.<br>
	 * Caso tenha, é criado um novo escopo binário.<br>
	 * Para esse novo escopo é retirado a posição direita do escopo anterior (que estava sendo controlado) e colocado a sua esquerda.<br>
	 * O novo operador é colocado nesse novo escopo também, e ele passa a ser o controlado.
	 * <p>2ª: Caso a primeira validação seja falsa, é criado um novo escopo binário,<br>
	 * Colocando o escopo anterior (que estava sendo controlado) à sua esquerda, e o novo operador no seu operador.
	 * Assim, esse escopo passa a ser o controlado.
	 * 
	 * @param operador novo operador a ser adicionado.
	 */
	public void setOperador(IOperador operador) {
		if (this.escopoBinario.currentElement().haveOperador()) {
			EscopoBinario escopoBinario = new EscopoBinario();
			if (operador.getPrioridade() > this.escopoBinario.currentElement().getOperador().getPrioridade()) {
				escopoBinario.setTipo(EscopoTipo.PRIORITARIO);
				escopoBinario.setEscopo(this.escopoBinario.currentElement().popEscopo());
			} else {
				escopoBinario.setTipo(EscopoTipo.NORMAL);
				escopoBinario.setEscopo(this.escopoBinario.pop());
			}
			
			this.escopoBinario.push(escopoBinario);
		}
		
		this.escopoBinario.currentElement().setOperador(operador);
	}
	
	/**
	 * Cria um novo escopo binário, e passa o controle para ele.
	 */
	public void abrirEscopoPrioritario() {
		EscopoBinario escopoBinario = new EscopoBinario();
		escopoBinario.setTipo(EscopoTipo.PARENTESES);
		this.escopoBinario.push(escopoBinario);
	}
	
	/**
	 * Fecha o escopo que está sendo controlado, colocando-o dentro do escopo anterior a ele (penúltimo da pilha).<br>
	 * Caso o escopo anterior seja do tipo prioritário (um operador com prioridade maior).<br>
	 * Verifica se ele está completo (com esquerda, operador e direita).<br>
	 * Caso esteja, usuando recursividade, é mandado fechar o denovo.
	 */
	public void fecharEscopoPrioritario() {
		EscopoBinario last = this.escopoBinario.pop();
		EscopoBinario currentElement = this.escopoBinario.currentElement();
		currentElement.setEscopo(last);
		
		if (EscopoTipo.PRIORITARIO.equals(currentElement.getTipo())) {
			if (currentElement.isComplete()) {
				this.fecharEscopoPrioritario();
			}
		}
	}
	
	/**
	 * Gera o resultado de todos os escopos.
	 * @return {@link BigDecimal} resultado.
	 */
	public BigDecimal close() {
		while (escopoBinario.size() > 1) {
			fecharEscopoPrioritario();
		}
		
		return escopoBinario.pop().close();
	}

}
