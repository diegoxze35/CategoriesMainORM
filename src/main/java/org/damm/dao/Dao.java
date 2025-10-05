package org.damm.dao;

import java.io.Closeable;
import java.util.List;

public interface Dao<ENTITY, ID> extends Closeable {
	List<ENTITY> findAll();
	ENTITY findById(ID id);
	ENTITY save(ENTITY entity);
	void delete(ENTITY entity);
}
