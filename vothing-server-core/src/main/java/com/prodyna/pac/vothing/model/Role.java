package com.prodyna.pac.vothing.model;

import java.util.Collection;

/**
 * Created by jrodan on 13/05/16.
 */
public interface Role extends BaseModel<Role>{

    Collection<Permission> getPermissions();

    void setPermissions(Collection<Permission> permissions);
}
