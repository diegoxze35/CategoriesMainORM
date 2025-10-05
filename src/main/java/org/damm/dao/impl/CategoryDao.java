package org.damm.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.io.Closeable;
import java.util.List;
import org.damm.dao.Dao;
import org.damm.entity.Category;

public class CategoryDao implements Dao<Category, Long>, Closeable {

	private final EntityManagerFactory entityManagerFactory;
	private final EntityManager entityManager;

	public CategoryDao() {
		entityManagerFactory = Persistence.createEntityManagerFactory("data");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@Override
	public List<Category> findAll() {
		String jpql = "SELECT c FROM Category c";
		TypedQuery<Category> query = entityManager.createQuery(jpql, Category.class);
		return query.getResultList();
	}

	@Override
	public Category findById(Long id) {
		return entityManager.find(Category.class, id);
	}

	@Override
	public Category save(Category entity) {
		entityManager.getTransaction().begin();
		Category newEntity = entityManager.merge(entity);
		entityManager.getTransaction().commit();
		return newEntity;
	}

	@Override
	public void delete(Category entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public void close() {
		entityManager.close();
		entityManagerFactory.close();
	}

}
