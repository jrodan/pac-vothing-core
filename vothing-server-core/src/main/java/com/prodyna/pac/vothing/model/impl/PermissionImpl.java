package com.prodyna.pac.vothing.model.impl;

import com.prodyna.pac.vothing.model.Permission;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "vothing_permission")
public class PermissionImpl extends BaseModelImpl<Permission> implements Permission {

	private static final long serialVersionUID = 4518806862505629512L;

	public PermissionImpl() {
	}
	
}
