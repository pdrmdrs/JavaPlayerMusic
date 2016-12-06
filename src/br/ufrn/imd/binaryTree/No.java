package br.ufrn.imd.binaryTree;

/**
 * 
 * Classe que representa o nó da árvore binária
 * 
 * @author pdr_m
 *
 * @param <T>
 *            tipo do nó que será salvo na árvore
 */
public class No<T extends Comparable<T>> implements Comparable<No<T>> {

	/**
	 * Atributo que representa o conteúdo do nó
	 */
	private T valor;

	/**
	 * Nó filho a direita do nó atual
	 */
	private No<T> direita;

	/**
	 * Nó filho a esquerda do nó atual
	 */
	private No<T> esquerda;

	/**
	 * Nó pai do no atual
	 */
	private No<T> pai;

	/**
	 * Construtor do nó passando como parâmetro o seu conteúdo
	 * 
	 * @param valor
	 *            do conteúdo do nó
	 */
	public No(T valor) {
		this.valor = valor;
	}

	/**
	 * Construtor do nó passando como parâmetro valores para todos os seus
	 * atributos
	 * 
	 * @param valor
	 *            do conteúdo do nó
	 * @param direita
	 *            nó filho a direita do nó atual
	 * @param esquerda
	 *            nó filho a esquerda do nó atual
	 * @param pai
	 *            nó pai do nó atual
	 */
	public No(T valor, No<T> direita, No<T> esquerda, No<T> pai) {
		super();
		this.valor = valor;
		this.direita = direita;
		this.esquerda = esquerda;
		this.pai = pai;
	}

	/**
	 * Pega o valor do nó
	 * 
	 * @return valor do conteúdo do nó
	 */
	public T getValor() {
		return valor;
	}

	/**
	 * Seta o valor do nó
	 * 
	 * @param valor
	 *            do conteúdo do nó
	 */
	public void setValor(T valor) {
		this.valor = valor;
	}

	/**
	 * Pega o nó filho a direita do nó atual
	 * 
	 * @return nó filho a direita do nó atual
	 */
	public No<T> getDireita() {
		return direita;
	}

	/**
	 * Seta o nó filho a direita do nó atual
	 * 
	 * @param direita
	 *            do nó atual
	 */
	public void setDireita(No<T> direita) {
		this.direita = direita;
	}

	/**
	 * Pega o nó filho a esquerda do nó atual
	 * 
	 * @return nó filho a esquerda do nó atual
	 */
	public No<T> getEsquerda() {
		return esquerda;
	}

	/**
	 * Seta o nó filho a esquerda do nó atual
	 * 
	 * @param esquerda
	 *            do nó atual
	 */
	public void setEsquerda(No<T> esquerda) {
		this.esquerda = esquerda;
	}

	/**
	 * Pega o nó pai do nó atual
	 * 
	 * @return nó pai do nó atual
	 */
	public No<T> getPai() {
		return pai;
	}

	/**
	 * Seta o nó pai do nó atual
	 * 
	 * @param pai
	 *            do nó atual
	 */
	public void setPai(No<T> pai) {
		this.pai = pai;
	}

	/**
	 * Método para comparar um nó com o outro afim de poder ser salvo na árvore
	 * binária
	 * 
	 * @param no
	 *            para ser comparado com o nó atual
	 */
	@Override
	public int compareTo(No<T> no) {
		return this.valor.compareTo(no.getValor());
	}

	/**
	 * toString para imprimir o valor correto do nó
	 */
	@Override
	public String toString() {
		return this.valor.toString();
	}

}
