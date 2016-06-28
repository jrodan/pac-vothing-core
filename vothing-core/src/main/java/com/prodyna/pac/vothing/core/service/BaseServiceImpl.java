package com.prodyna.pac.vothing.core.service;

import com.prodyna.pac.vothing.api.constants.EntityOrder;
import com.prodyna.pac.vothing.api.model.BaseModel;
import com.prodyna.pac.vothing.api.service.BaseService;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

public class BaseServiceImpl<T extends BaseModel> implements BaseService<T> {

	@Inject
	private Logger logger;

	@Inject
	private EntityManager entityManager;

	@Override
	public List<T> getElements() {
		return this.getElements(new EntityOrder());
	}

	@Override
	public List<T> getElements(EntityOrder entityOrder) {

		Class<T> persistentClass = (Class<T>) getEntityImplClass();

		String orderString = entityOrder.getOrder();

		Query query = entityManager.createQuery(
				"SELECT e FROM " + getEntityInterfaceClass().getSimpleName() + " e order by " +
						orderString);
		List<T> entities = (List<T>) query.getResultList();

		return entities;
	}

	@Override
	public <T> T getElement(long id) {

		Class<T> persistentClass = (Class<T>) getEntityImplClass();
		T element = null;

		element = entityManager.find(
				persistentClass, id);
		if (element == null) {
			throw new EntityNotFoundException(
					persistentClass.getSimpleName() + " could not be found for given id [" + id
							+ "]");
		}

		return element;
	}

	private Class<T> getEntityInterfaceClass() {
		Class<T> persistentInterface = (Class<T>)
				((ParameterizedType) getClass().getGenericSuperclass())
						.getActualTypeArguments()[0];
		return persistentInterface;
	}

	private Class<T> getEntityImplClass() {
		Class<T> persistentClass = null;
		Class<T> persistentInterface = getEntityInterfaceClass();

		String implClassName =
				"com.prodyna.pac.vothing.core.model." + persistentInterface.getSimpleName() +
						"Impl";

		try {
			persistentClass = (Class<T>) Class.forName(implClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); // TODO
		}

		return persistentClass;
	}

	@Override
	public <T> void deleteElement(long id) {
		T element = this.getElement(id);
		entityManager.remove(element);

	}

	@Override
	public <T extends BaseModel> T addElement(T element) {

		if (element.getId() > 0) {
			T dbElement = getElement(element.getId());
			if (dbElement != null) {
				this.updateElement(element);
			} else {
				// TODO throw error in this case?
				// this.vothing.getEntityManager().persist(element);
			}

		} else {
			element.setCreateDate(new Date());
			element.setModifiedDate(new Date());
			entityManager.persist(element);
		}

		return element;
	}

	@Override
	public <T extends BaseModel> T updateElement(T element) {

		T dbElement = getElement(element.getId());
		if (dbElement != null) {
			element.setModifiedDate(new Date());
			element = entityManager.merge(element);
		} else {
			// TODO throw error
		}

		return element;

	}

}
