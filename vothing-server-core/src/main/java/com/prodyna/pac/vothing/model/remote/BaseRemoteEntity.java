package com.prodyna.pac.vothing.model.remote;

import java.util.List;

/**
 * Created by jrodan on 13/05/16.
 */
@SuppressWarnings("ALL")
public interface BaseRemoteEntity {

	boolean getUserVotedThisOption();

	void setUserVotedThisOption(boolean userVotedThisOption);

	List<String> getUsersPermissions();

	void setUsersPermissions(List<String> usersPermissions);

	boolean isNew();

	void setIsNew(boolean isNew);
}
