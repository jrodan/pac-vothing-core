package com.prodyna.pac.vothing.api.model;

import com.prodyna.pac.vothing.api.model.api.RoleInterface;

import javax.persistence.MappedSuperclass;

/**
 * Created by jrodan on 21/06/16.
 */
@MappedSuperclass
public abstract class Role extends BaseModelImpl<Role> implements RoleInterface {
}
