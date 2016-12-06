package br.ufrn.imd.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.ufrn.imd.binaryTree.ArvoreBinaria;

public class ArvoreBinariaTest {

	private ArvoreBinaria<Integer> arvore;

	@Before
	public void setUp() throws Exception {
		this.arvore = new ArvoreBinaria<Integer>();

		this.arvore.inserir(15);
		this.arvore.inserir(6);
		this.arvore.inserir(18);
		this.arvore.inserir(3);
		this.arvore.inserir(7);
		this.arvore.inserir(17);
		this.arvore.inserir(20);
		this.arvore.inserir(2);
		this.arvore.inserir(4);
		this.arvore.inserir(13);
		this.arvore.inserir(9);
	}

	@Test
	public void testBuscar() {
		assertEquals(new Integer(6), this.arvore.buscar(6).getValor());
	}

	@Test
	public void testInserir() {
		this.arvore.inserir(72);
		assertEquals(new Integer(72), this.arvore.buscar(72).getValor());
	}

	@Test
	public void testDeletar() {
		this.arvore.deletar(6);
		assertNull(this.arvore.buscar(6));
		this.arvore.percussoEmOrdem();
	}

	@Test
	public void testPercussoEmOrdem() {
		this.arvore.percussoEmOrdem();
	}

}
