package org.damm.dao.impl;

import org.damm.dao.Dao;
import org.damm.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class CategoryDao implements Dao<Category, Integer> {
	
	private EntityManager em;
	
	public CategoryDao() {
		this.em = Persistence.createEntityManagerFactory("category").createEntityManager();
	}
	
	@Override
	public Category findById(Integer integer) {
		return null;
	}
	
	@Override
	public Category save(Category category) {
		return null;
	}
	
	@Override
	public void delete(Category category) {
	
	}
}
