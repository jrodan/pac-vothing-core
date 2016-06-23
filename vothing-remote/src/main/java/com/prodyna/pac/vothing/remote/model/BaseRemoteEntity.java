package com.prodyna.pac.vothing.remote.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by jrodan on 13/05/16.
 */
public interface BaseRemoteEntity extends Serializable {

	long getObjectKey();

	void setObjectKey(long objectKey);

	long getId();

	void setId(long id);

	String getName();

	void setName(String name);

	Date getModifiedDate();

	void setModifiedDate(Date modifiedDate);

	Date getCreateDate();

	void setCreateDate(Date createDate);

	boolean getUserVotedThisOption();

	void setUserVotedThisOption(boolean userVotedThisOption);

	List<String> getUsersPermissions();

	void setUsersPermissions(List<String> usersPermissions);

	boolean isNew();

	void setIsNew(boolean isNew);
}
