package com.prodyna.pac.vothing.model.remote;

import java.util.List;

/**
 * Created by jrodan on 13/05/16.
 */
public interface BaseRemoteEntity {

    List<String> getUsersPermissions();

    void setUsersPermissions(List<String> usersPermissions);
}
