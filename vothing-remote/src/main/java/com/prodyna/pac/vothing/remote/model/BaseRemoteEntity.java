package com.prodyna.pac.vothing.remote.model;

import java.util.List;

/**
 * Created by jrodan on 13/05/16.
 */
public interface BaseRemoteEntity {

	boolean getUserVotedThisOption();

	void setUserVotedThisOption(boolean userVotedThisOption);

	List<String> getUsersPermissions();

	void setUsersPermissions(List<String> usersPermissions);

	boolean isNew();

	void setIsNew(boolean isNew);
}
