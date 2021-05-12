package com.wipro.desafiosefaz.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.wipro.desafiosefaz.model.Usuario;
import com.wipro.desafiosefaz.util.JPAUtil;


public abstract class DAO<E> {

	public void salvar(E entidade){
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		entityManager.persist(entidade);
		
		entityTransaction.commit();
		entityManager.close();
	}
	
	public E merge(E entidade){
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		E retorno = entityManager.merge(entidade);

		entityTransaction.commit();
		entityManager.close();
		
		return retorno;
	}
	
	public void delete(E entidade){
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		entityManager.remove(entidade);
		
		entityTransaction.commit();
		entityManager.close();
	}
	
	//metodo criado para contornar o erro: java.lang.IllegalArgumentException: Removing a detached instance
	public void deletePorId(E entidade){
		EntityManager entityManager = JPAUtil.getEntityManager();
		entityManager.getTransaction().begin();
		
		Object id = JPAUtil.getPrimaryKey(entidade);
		
		E foo = (E) entityManager.find(entidade.getClass(),id);
		
		entityManager.remove(foo);
		entityManager.getTransaction().commit();
	    entityManager.clear();
	}
	
	public List<E> getListEntity(Class<E> entidade){
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		List<E> retorno = entityManager.createQuery("from " + entidade.getName()).getResultList();
		
		entityTransaction.commit();
		entityManager.close();
		
		return retorno;
	}

	

}