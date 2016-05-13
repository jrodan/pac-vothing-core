package com.prodyna.pac.vothing.model;

import java.util.Date;

public interface BaseModel<T> {

	<T> long getId();
	
	<T> void setId(long id);

	<T> String getName();

	<T> void setName(String name);

	<T> Date getModifiedDate();

	<T> void setModifiedDate(Date modifiedDate);

	<T> Date getCreateDate();

	<T> void setCreateDate(Date createDate);
}
