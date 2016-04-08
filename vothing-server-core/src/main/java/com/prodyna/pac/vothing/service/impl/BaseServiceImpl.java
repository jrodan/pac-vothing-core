package com.prodyna.pac.vothing.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;

import com.prodyna.pac.vothing.Vothing;
import com.prodyna.pac.vothing.constants.VothingConstants;
import com.prodyna.pac.vothing.persistence.BaseModel;
import com.prodyna.pac.vothing.service.BaseService;

public class BaseServiceImpl<T extends BaseModel> implements BaseService<T> {
	
	@Inject
	private Logger logger;

	@Inject
	private Vothing vothing;

	@Override
	public List<T> getElements() {
		
		Class<T> persistentClass = (Class<T>)
				   ((ParameterizedType)getClass().getGenericSuperclass())
				      .getActualTypeArguments()[0];
		
		String classString = VothingConstants.SELECT_ALL + persistentClass.getSimpleName().toLowerCase() + "s";
		
		List<T> permissions = this.vothing
				.getEntityManager()
				.createNamedQuery(classString,
						persistentClass).getResultList();

		return permissions;
	}

	@Override
	public <T> T getElement(long id) {
		
		Class<T> persistentClass = (Class<T>)
				   ((ParameterizedType)getClass().getGenericSuperclass())
				      .getActualTypeArguments()[0];
		
		T element = this.vothing.getEntityManager().find(
				persistentClass, id);
		if (element == null) {
			throw new EntityNotFoundException(
					persistentClass.getSimpleName() + " could not be found for given id [" + id
							+ "]");
		}

		return element;
	}

	@Override
	public <T> void deleteElement(long id) {
		T element = this.getElement(id);
		this.vothing.getEntityManager().remove(element);
		
	}

	@Override
	public <T extends BaseModel> T addElement(T element) {
		
		if(element.getId() > 0) {
			T dbElement = getElement(element.getId());
			if(dbElement != null) {
				this.vothing.getEntityManager().refresh(element);
			} else {
				this.vothing.getEntityManager().persist(element);
			}
			
		} else {
			this.vothing.getEntityManager().persist(element);
		}
		
		return element;
		
	}

}
