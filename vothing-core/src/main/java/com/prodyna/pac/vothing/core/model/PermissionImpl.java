package com.prodyna.pac.vothing.core.model;

import com.prodyna.pac.vothing.api.model.Permission;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "Permission")
@XmlRootElement
@Table(name = "vothing_permission")
public class PermissionImpl extends Permission {

	private static final long serialVersionUID = 4518806862505629512L;

	public PermissionImpl() {
	}

}
