package com.prodyna.pac.vothing.remote.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.gson.Gson;
import com.prodyna.pac.vothing.api.model.BaseModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "objectKey")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseRemoteEntityImpl implements BaseRemoteEntity {

	private long id;

	private long objectKey = new Random().nextLong();

	private String name;

	private Date createDate;

	private Date modifiedDate;

	private boolean userVotedThisOption = false;

	private List<String> usersPermissions = new ArrayList<>();

	private boolean isNew = false;

	private boolean userVoted = false;

	private int votes = 0;

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
	public boolean getUserVotedThisOption() {
		return this.userVotedThisOption;
	}

	@Override
	public void setUserVotedThisOption(boolean userVotedThisOption) {
		this.userVotedThisOption = userVotedThisOption;
	}

	@Override
	public List<String> getUsersPermissions() {
		return this.usersPermissions;
	}

	@Override
	public void setUsersPermissions(List<String> usersPermissions) {
		this.usersPermissions = usersPermissions ;
	}

	@Override
	public boolean isNew() {
		return isNew;
	}

	@Override
	public void setIsNew(boolean isNew) {
		this.isNew = isNew;
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
