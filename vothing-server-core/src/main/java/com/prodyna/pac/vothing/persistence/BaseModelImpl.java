package com.prodyna.pac.vothing.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;

@XmlRootElement
@Inheritance
@MappedSuperclass
public abstract class BaseModelImpl<T> implements BaseModel<T>, Serializable {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column
	private String name;
	
	@Override
	public long getId() {
		return id;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BaseModel)) {
			return false;
		}
		final BaseModel other = (BaseModel) obj;
		if (this.getId() == 0) {
			if (other.getId() != 0) {
				return false;
			}
		} else if (this.getId() == other.getId()) {
			return false;
		}
		return true;
	}
}
