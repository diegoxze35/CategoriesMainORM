package org.damm.dao;

public interface Dao<ENTITY, ID> {
	public ENTITY findById(ID id);
	public ENTITY save(ENTITY entity);
	public void delete(ENTITY entity);
}
