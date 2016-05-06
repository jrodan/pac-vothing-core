package com.prodyna.pac.vothing.service;

import com.prodyna.pac.vothing.persistence.BaseModel;

import java.util.List;

public interface BaseService<T extends BaseModel> {

    List<T> getElements();

    <T> T getElement(long id);

    <T> void deleteElement(long id);

    <T extends BaseModel> T addElement(T element);

    <T extends BaseModel> T updateElement(T element);

}
