package br.ufrn.imd.binaryTree;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.domain.User;

/**
 * 
 * Adapter for the Binary Search Tree class
 * 
 * @author pdr_m
 *
 * @param <User>
 */
public class ArvoreBinariaAdapter {
	
	private static ArvoreBinaria<User> arvoreBinaria = new ArvoreBinaria<User>();
	private static List<User> lista = new ArrayList<>();
	
	public List<User> returnAsList() {
//		this.lista.clear();
		this.percursoEmOrdem(this.arvoreBinaria.getRaiz());
		return lista;
	}
	
	public void saveAsBinarySearchTree(List<User> usersList) {
		for(User u : usersList) {
			this.arvoreBinaria.inserir(u);
		}
	}
	
//	private List<User> percussoEmOrdemStart() {
//		List<User> result = new ArrayList<User>();
//		
//		result.add(this.percursoEmOrdem(this.arvoreBinaria.getRaiz()));
//		
//		return result;
//	}

	private void percursoEmOrdem(No<User> no) {
		
//		User u = new User();
//		
//		if (no != null) {
//			this.percursoEmOrdem(no.getEsquerda());
//			u = no.getValor();
//			this.percursoEmOrdem(no.getDireita());
//		}
//		
//		return u;
		
		if (no == null)
			return;

		this.lista.add(no.getValor());

		this.percursoEmOrdem(no.getEsquerda());
		this.percursoEmOrdem(no.getDireita());
		
	}
	
	public List<User> getLista() {
		return this.lista;
	}
	
	public ArvoreBinaria<User> getArvoreBinaria() {
		return this.arvoreBinaria;
	}
	
}
