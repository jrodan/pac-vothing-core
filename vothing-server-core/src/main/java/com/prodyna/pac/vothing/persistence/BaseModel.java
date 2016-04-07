package com.prodyna.pac.vothing.persistence;

public interface BaseModel<T> {
	
	<T> long getId();
	
	<T> void setId(long id);

	<T> String getName();

	<T> void setName(String name);

}
