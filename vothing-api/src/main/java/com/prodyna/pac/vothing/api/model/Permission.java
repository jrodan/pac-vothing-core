package com.prodyna.pac.vothing.api.model;

import com.prodyna.pac.vothing.api.model.api.PermissionInterface;

import javax.persistence.MappedSuperclass;

/**
 * Created by jrodan on 21/06/16.
 */
@MappedSuperclass
public abstract class Permission extends BaseModelImpl<Permission> implements PermissionInterface {
}
