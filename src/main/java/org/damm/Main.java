package org.damm;

import javax.swing.SwingUtilities;
import org.damm.dao.Dao;
import org.damm.dao.impl.CategoryDao;
import org.damm.dao.impl.EventDao;
import org.damm.entity.Category;
import org.damm.entity.Event;
import org.damm.ui.CrudUI;

public class Main {
	public static void main(String[] args) {
		final Dao<Category, Integer> categoryDao = new CategoryDao();
		final Dao<Event, Integer> eventDao = new EventDao();
		SwingUtilities.invokeLater(() -> new CrudUI(categoryDao, eventDao).setVisible(true));
	}
}
