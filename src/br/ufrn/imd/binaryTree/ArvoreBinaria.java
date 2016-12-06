package br.ufrn.imd.binaryTree;

/**
 * 
 * Classe que representa uma �rvore bin�ria de busca
 * 
 * @author pdr_m
 *
 * @param <T> tipo da �rvore
 */
public class ArvoreBinaria<T extends Comparable<T>> {

	/**
	 * N� raiz da �rvore
	 */
	private No<T> raiz;

	/**
	 * Construtor vazio
	 */
	public ArvoreBinaria() {

	}

	/**
	 * Construtor passando o valor do seu n� raiz
	 * 
	 * @param valor conte�do do n� raiz
	 */
	public ArvoreBinaria(T valor) {
		this.raiz = new No<T>(valor);
	}

	/**
	 * Pega o n� raiz
	 * 
	 * @return raiz
	 */
	public No<T> getRaiz() {
		return raiz;
	}

	/**
	 * Seta o n� raiz
	 * 
	 * @param raiz da �rvore
	 */
	public void setRaiz(No<T> raiz) {
		this.raiz = raiz;
	}

	/**
	 * Busca um n� na �rvore que cont�m o valor passado
	 * 
	 * @param valor
	 *            valor do n� � ser buscado na �rvore
	 * @return n� que cont�m o valor buscado
	 */
	public No<T> buscar(T valor) {
		No<T> resultado = this.buscar(this.raiz, valor);
		return resultado;
	}

	private No<T> buscar(No<T> no, T valor) {
		if (no == null || no.getValor() == valor) {
			return no;
		}
		if (valor.compareTo(no.getValor()) < 0) {
			return this.buscar(no.getEsquerda(), valor);
		} else {
			return this.buscar(no.getDireita(), valor);
		}
	}

	/**
	 * Insere um novo n� na �rvore que tem como valor o valor passado
	 * 
	 * @param valor
	 *            valor do n� que ser� inserido na �rvore
	 */
	public void inserir(T valor) {

		No<T> noInserido = new No<T>(valor);

		No<T> no1 = null;
		No<T> no2 = this.raiz;

		while (no2 != null) {
			no1 = no2;
			if (noInserido.compareTo(no2) < 0) {
				no2 = no2.getEsquerda();
			} else {
				no2 = no2.getDireita();
			}
		}

		noInserido.setPai(no1);

		if (no1 == null) {
			this.setRaiz(noInserido);
		} else if (noInserido.compareTo(no1) < 0) {
			no1.setEsquerda(noInserido);
		} else {
			no1.setDireita(noInserido);
		}
	}

	/**
	 * Deleta um n� que cont�m o valor passado como par�metro
	 * 
	 * @param valor
	 *            do n� que ser� deletado
	 * @return o n� que fora deletado
	 */
	public No<T> deletar(T valor) {

		No<T> noParaDeletar = this.buscar(valor);// z

		No<T> noSucessor = null;// y

		No<T> noAux = null;// x

		if (noParaDeletar.getEsquerda() == null || noParaDeletar.getDireita() == null) {
			noSucessor = noParaDeletar;
		} else {
			noSucessor = this.getSucessor(noParaDeletar);
		}

		if (noSucessor.getEsquerda() == null) {
			noAux = noSucessor.getEsquerda();
		} else {
			noAux = noSucessor.getDireita();
		}

		if (noAux != null) {
			noAux.setPai(noSucessor.getPai());
		}

		if (noSucessor.getPai() == null) {
			this.setRaiz(noAux);
		} else if (noSucessor.compareTo(noSucessor.getPai().getEsquerda()) == 0) {
			noSucessor.getPai().setEsquerda(noAux);
		} else {
			noSucessor.getPai().setDireita(noAux);
		}

		if (noSucessor.compareTo(noParaDeletar) != 0) {
			noParaDeletar.setValor(noSucessor.getValor());
		}

		return noSucessor;
	}

	private No<T> getSucessor(No<T> no) {

		No<T> resultado;

		if (no.getDireita() != null) {
			return this.getMinimo(no.getDireita());
		}

		resultado = no.getPai();

		while (resultado != null && no.compareTo(resultado.getDireita()) == 0) {
			no = resultado;
			resultado = no.getPai();
		}

		return resultado;
	}

	private No<T> getMinimo(No<T> no) {
		while (no.getEsquerda() != null) {
			no = no.getDireita();
		}
		return no;
	}

	/**
	 * Imprime os n�s da �rvore com o caminho EmOrdem
	 */
	public void percussoEmOrdem() {
		this.percursoEmOrdem(this.raiz);
	}

	private void percursoEmOrdem(No<T> no) {
		if (no != null) {
			this.percursoEmOrdem(no.getEsquerda());
			System.out.println(no);
			this.percursoEmOrdem(no.getDireita());
		}
	}
}