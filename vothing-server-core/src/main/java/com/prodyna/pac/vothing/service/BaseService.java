package com.prodyna.pac.vothing.service;

import java.util.List;

import com.prodyna.pac.vothing.persistence.BaseModel;

public interface BaseService<T extends BaseModel> {
	
	List<T> getElements();
	
	<T> T getElement(long id);
	
	<T> void deleteElement(long id);
	
	<T extends BaseModel> T addElement(T element);

}
