package com.prodyna.pac.vothing.model.impl;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "vothing_permission")
public class Permission extends BaseModelImpl<Permission> implements BaseModel<Permission> {

	private static final long serialVersionUID = 4518806862505629512L;

	public Permission() {
	}
	
}
