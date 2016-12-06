package br.ufrn.imd.binaryTree;

/**
 * 
 * Classe que representa uma árvore binária de busca
 * 
 * @author pdr_m
 *
 * @param <T> tipo da árvore
 */
public class ArvoreBinaria<T extends Comparable<T>> {

	/**
	 * Nó raiz da árvore
	 */
	private No<T> raiz;

	/**
	 * Construtor vazio
	 */
	public ArvoreBinaria() {

	}

	/**
	 * Construtor passando o valor do seu nó raiz
	 * 
	 * @param valor conteúdo do nó raiz
	 */
	public ArvoreBinaria(T valor) {
		this.raiz = new No<T>(valor);
	}

	/**
	 * Pega o nó raiz
	 * 
	 * @return raiz
	 */
	public No<T> getRaiz() {
		return raiz;
	}

	/**
	 * Seta o nó raiz
	 * 
	 * @param raiz da árvore
	 */
	public void setRaiz(No<T> raiz) {
		this.raiz = raiz;
	}

	/**
	 * Busca um nó na árvore que contém o valor passado
	 * 
	 * @param valor
	 *            valor do nó à ser buscado na árvore
	 * @return nó que contém o valor buscado
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
	 * Insere um novo nó na árvore que tem como valor o valor passado
	 * 
	 * @param valor
	 *            valor do nó que será inserido na árvore
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
	 * Deleta um nó que contém o valor passado como parâmetro
	 * 
	 * @param valor
	 *            do nó que será deletado
	 * @return o nó que fora deletado
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
	 * Imprime os nós da árvore com o caminho EmOrdem
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