package com.wipro.desafiosefaz.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import com.wipro.desafiosefaz.model.Usuario;
import com.wipro.desafiosefaz.util.JPAUtil;

public class UsuarioDAO extends DAO<Usuario> {
	
	public Usuario consultarUsuario(String login, String senha) {
		
		Usuario usuario = null;
		try{
			

			EntityManager entityManager = JPAUtil.getEntityManager();
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			usuario = (Usuario) entityManager.createQuery("select u from Usuario u where u.login = '" + login + "' and u.senha = '" + senha + "'").getSingleResult();

			entityTransaction.commit();
			entityManager.close();

			
		}catch(NoResultException e){
			System.out.println("Login incorreto ou usuário inexistente");
		}
		return usuario;
	}
	
	public void deletePorLogin(Usuario usuario){
		EntityManager entityManager = JPAUtil.getEntityManager();
		entityManager.getTransaction().begin();
		
		Object id = JPAUtil.getPrimaryKey(usuario);
		
		Usuario foo = entityManager.find(Usuario.class,id);
		
		entityManager.remove(foo);
		entityManager.getTransaction().commit();
	    entityManager.clear();
	}
}
