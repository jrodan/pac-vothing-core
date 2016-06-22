package com.prodyna.pac.vothing.api.model;

import com.prodyna.pac.vothing.api.model.api.UserInterface;

import javax.persistence.MappedSuperclass;

/**
 * Created by jrodan on 21/06/16.
 */
@MappedSuperclass
public abstract class User extends BaseModelImpl<User> implements UserInterface {

}
