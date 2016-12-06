package br.ufrn.imd.binaryTree;

/**
 * 
 * Classe que representa o n� da �rvore bin�ria
 * 
 * @author pdr_m
 *
 * @param <T>
 *            tipo do n� que ser� salvo na �rvore
 */
public class No<T extends Comparable<T>> implements Comparable<No<T>> {

	/**
	 * Atributo que representa o conte�do do n�
	 */
	private T valor;

	/**
	 * N� filho a direita do n� atual
	 */
	private No<T> direita;

	/**
	 * N� filho a esquerda do n� atual
	 */
	private No<T> esquerda;

	/**
	 * N� pai do no atual
	 */
	private No<T> pai;

	/**
	 * Construtor do n� passando como par�metro o seu conte�do
	 * 
	 * @param valor
	 *            do conte�do do n�
	 */
	public No(T valor) {
		this.valor = valor;
	}

	/**
	 * Construtor do n� passando como par�metro valores para todos os seus
	 * atributos
	 * 
	 * @param valor
	 *            do conte�do do n�
	 * @param direita
	 *            n� filho a direita do n� atual
	 * @param esquerda
	 *            n� filho a esquerda do n� atual
	 * @param pai
	 *            n� pai do n� atual
	 */
	public No(T valor, No<T> direita, No<T> esquerda, No<T> pai) {
		super();
		this.valor = valor;
		this.direita = direita;
		this.esquerda = esquerda;
		this.pai = pai;
	}

	/**
	 * Pega o valor do n�
	 * 
	 * @return valor do conte�do do n�
	 */
	public T getValor() {
		return valor;
	}

	/**
	 * Seta o valor do n�
	 * 
	 * @param valor
	 *            do conte�do do n�
	 */
	public void setValor(T valor) {
		this.valor = valor;
	}

	/**
	 * Pega o n� filho a direita do n� atual
	 * 
	 * @return n� filho a direita do n� atual
	 */
	public No<T> getDireita() {
		return direita;
	}

	/**
	 * Seta o n� filho a direita do n� atual
	 * 
	 * @param direita
	 *            do n� atual
	 */
	public void setDireita(No<T> direita) {
		this.direita = direita;
	}

	/**
	 * Pega o n� filho a esquerda do n� atual
	 * 
	 * @return n� filho a esquerda do n� atual
	 */
	public No<T> getEsquerda() {
		return esquerda;
	}

	/**
	 * Seta o n� filho a esquerda do n� atual
	 * 
	 * @param esquerda
	 *            do n� atual
	 */
	public void setEsquerda(No<T> esquerda) {
		this.esquerda = esquerda;
	}

	/**
	 * Pega o n� pai do n� atual
	 * 
	 * @return n� pai do n� atual
	 */
	public No<T> getPai() {
		return pai;
	}

	/**
	 * Seta o n� pai do n� atual
	 * 
	 * @param pai
	 *            do n� atual
	 */
	public void setPai(No<T> pai) {
		this.pai = pai;
	}

	/**
	 * M�todo para comparar um n� com o outro afim de poder ser salvo na �rvore
	 * bin�ria
	 * 
	 * @param no
	 *            para ser comparado com o n� atual
	 */
	@Override
	public int compareTo(No<T> no) {
		return this.valor.compareTo(no.getValor());
	}

	/**
	 * toString para imprimir o valor correto do n�
	 */
	@Override
	public String toString() {
		return this.valor.toString();
	}

}
