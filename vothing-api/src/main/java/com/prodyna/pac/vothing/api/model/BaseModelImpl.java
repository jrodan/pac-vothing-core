package com.prodyna.pac.vothing.api.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.gson.Gson;
import com.prodyna.pac.vothing.api.Vothing;

import javax.inject.Inject;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Random;

@XmlRootElement
@Inheritance
@MappedSuperclass
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "objectKey")
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseModelImpl<T> implements BaseModel<T> {

	@Inject
	@Transient
	private Vothing vothing;

	@Id
	@GeneratedValue
	private long id;

	@Transient
	private long objectKey = new Random().nextLong();

	@Column
	private String name;

	@Column
	private Date createDate;

	@Column
	private Date modifiedDate;

	@Override
	public long getObjectKey() {
		return objectKey;
	}

	@Override
	public void setObjectKey(long objectKey) {
		this.objectKey = objectKey;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
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
	public Date getModifiedDate() {
		return modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public Date getCreateDate() {
		return createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
		return this.getId() == other.getId();
	}
}
