package org.damm.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.damm.dao.Dao;
import org.damm.entity.Event;

public class EventDao implements Dao<Event, Integer> {

	private final EntityManagerFactory entityManagerFactory;
	private final EntityManager entityManager;

	public EventDao() {
		entityManagerFactory = Persistence.createEntityManagerFactory("data");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@Override
	public List<Event> findAll() {
		String jpql = "SELECT c FROM Event c";
		TypedQuery<Event> query = entityManager.createQuery(jpql, Event.class);
		return query.getResultList();
	}

	@Override
	public Event findById(Integer id) {
		return entityManager.find(Event.class, id);
	}

	@Override
	public Event save(Event entity) {
		entityManager.getTransaction().begin();
		Event newEntity = entityManager.merge(entity);
		entityManager.getTransaction().commit();
		return newEntity;
	}

	@Override
	public void delete(Event entity) {
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
