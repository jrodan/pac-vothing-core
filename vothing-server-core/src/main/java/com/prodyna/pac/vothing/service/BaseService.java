package com.prodyna.pac.vothing.service;

import com.prodyna.pac.vothing.model.helper.EntityOrder;
import com.prodyna.pac.vothing.model.impl.BaseModel;

import java.util.List;

public interface BaseService<T extends BaseModel> {

	List<T> getElements();

	List<T> getElements(EntityOrder entityOrder);

	<T> T getElement(long id);

	<T> void deleteElement(long id);

	<T extends BaseModel> T addElement(T element);

	<T extends BaseModel> T updateElement(T element);

}
