package org.damm;

import java.io.IOException;
import java.time.LocalDateTime;
import org.damm.dao.Dao;
import org.damm.dao.impl.CategoryDao;
import org.damm.entity.Category;

public class Main {
	public static void main(String[] args) throws IOException {
		Dao<Category, Long> categoryDao = new CategoryDao();
		Category category = new Category();
		category.setName("Test");
		category.setDescription("Test description");
		category.setCreatedAt(LocalDateTime.now());
		var inserted = categoryDao.save(category);
		System.out.println(inserted);
		categoryDao.findAll().forEach(System.out::println);
		inserted.setName("Test UPDATED");
		System.out.println(categoryDao.save(inserted));
		categoryDao.findAll().forEach(System.out::println);
		categoryDao.delete(inserted);
		categoryDao.findAll().forEach(System.out::println);
		categoryDao.close();
	}
}
