package com.prodyna.pac.vothing.persistence;

import java.util.Date;
import java.util.List;

public interface BaseModel<T> {

	List<String> getUsersPermissions();

	void setUsersPermissions(List<String> usersPermissions);

	<T> long getId();
	
	<T> void setId(long id);

	<T> String getName();

	<T> void setName(String name);

	<T> Date getModifiedDate();

	<T> void setModifiedDate(Date modifiedDate);

	<T> Date getCreateDate();

	<T> void setCreateDate(Date createDate);
}
