package com.prodyna.pac.vothing.persistence;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.gson.Gson;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Inheritance
@MappedSuperclass
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
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
	public int hashCode() {
		int hash = 17;
		hash = hash * 31 + Long.valueOf(this.getId()).intValue();
		hash = hash * 31 + this.getName().hashCode();
		hash = hash * 31 + this.getClass().getName().hashCode();
		return hash;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof BaseModel)) {
			return false;
		}
		final BaseModel other = (BaseModel) obj;
		if(this.getId() == other.getId()) {
			return true;
		}
		return false;
	}
}
