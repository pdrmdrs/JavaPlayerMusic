package br.ufrn.imd.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.ufrn.imd.binaryTree.ArvoreBinariaAdapter;
import br.ufrn.imd.domain.User;

public class ArvoreBinariaWithUserTest {

	private ArvoreBinariaAdapter arvore;
	
	@Before
	public void setUp() throws Exception {
		this.arvore = new ArvoreBinariaAdapter();
	}

	@Test
	public void test() {
//		this.arvore.inserir(new User("user1", "123", true, "user1"));
//		this.arvore.inserir(new User("user2", "123", true, "user2"));
//		this.arvore.inserir(new User("user3", "123", true, "user3"));
//		this.arvore.inserir(new User("user4", "123", true, "user4"));
//		this.arvore.inserir(new User("user5", "123", true, "user5"));
		
		this.arvore.returnAsList().add(new User("user1", "123", true, "user1"));
		this.arvore.returnAsList().add(new User("user2", "123", true, "user2"));
		this.arvore.returnAsList().add(new User("user3", "123", true, "user3"));
		this.arvore.returnAsList().add(new User("user4", "123", true, "user4"));
		this.arvore.returnAsList().add(new User("user5", "123", true, "user5"));
		
		List<User> lista = this.arvore.returnAsList();
		
//		for(User u : lista) {
//			System.out.println(u.getName() + " " +u.getId());
//		}
		
		List<User> listaTest = new ArrayList<>();
		
		listaTest.add(new User("u1", "123", true, "u1"));
		listaTest.add(new User("u2", "123", true, "u2"));
		listaTest.add(new User("u3", "123", true, "u3"));
		listaTest.add(new User("u4", "123", true, "u4"));
		listaTest.add(new User("u5", "123", true, "u5"));
		
		this.arvore.saveAsBinarySearchTree(listaTest);
		
		for(User u : this.arvore.returnAsList()){
			System.out.println(u.getName() + " " +u.getId());
		}

	}

}
