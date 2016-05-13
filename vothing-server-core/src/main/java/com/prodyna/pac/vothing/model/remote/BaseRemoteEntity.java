package com.prodyna.pac.vothing.model.remote;

import com.prodyna.pac.vothing.model.BaseModel;

import java.util.List;

/**
 * Created by jrodan on 13/05/16.
 */
public interface BaseRemoteEntity<T> extends BaseModel<T> {

    List<String> getUsersPermissions();

    void setUsersPermissions(List<String> usersPermissions);
}
