package com.prodyna.pac.vothing.service.impl;

import com.prodyna.pac.vothing.persistence.BaseModel;
import com.prodyna.pac.vothing.service.BaseService;
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

        Class<T> persistentClass = (Class<T>)
                ((ParameterizedType) getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0];

        Query query = entityManager.createQuery("SELECT e FROM " + persistentClass.getSimpleName() + " e");
        List<T> entities = (List<T>) query.getResultList();

        return entities;
    }

    @Override
    public <T> T getElement(long id) {

        Class<T> persistentClass = (Class<T>)
                ((ParameterizedType) getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0];

        T element = entityManager.find(
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
